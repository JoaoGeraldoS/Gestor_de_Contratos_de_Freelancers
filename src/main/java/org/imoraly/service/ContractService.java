package org.imoraly.service;

import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IContractRepository;

public class ContractService {

    private final IContractRepository repository;
    private final double[] porcentagem = {5.0, 10.0, 30.0};

    public ContractService(IContractRepository repository) {
        this.repository = repository;
    }

    public void createContract(Contract contract) {

        if(contract.getContractedHours() <= 0) {
            throw new RuntimeException("A hora deve ser maior que 0");
        }

        for (double valor : porcentagem){
            if(contract.getTax() != valor) {
                System.out.println(contract.getTax());
                System.out.println(valor);
                throw new RuntimeException("A porcentagem deve ser entre 5 a 30%");
            }
            contract.setTax(valor);
            break;
        }

        contract.setStatus(ContractEnum.ATIVO.toString());
        repository.createContract(contract);
    }

    public void readContracts() {
        repository.readContract().forEach(System.out::println);
    }

    public Contract readOneContract(int id) {
        Contract contract = repository.readOneContract(id);

        if(contract == null) {
            throw new RuntimeException("Contrato não existe");
        }

       return contract;
    }

    public void updateContract(Contract contract, int id) {

        repository.updateContract(contract, id);
    }

    public void verifyContractFreelancerAndClient(int freelaID, int clientID) {
        Contract freela = repository.readContractForFreelancerAndClient(freelaID, clientID);

        if (freela.getStatus().equals(ContractEnum.ATIVO.toString())) {
            throw new RuntimeException("Ja existe um contrato ativo entre as partes");
        }
    }

    public void terminateOrCancelContract(Contract contract, int id) {
        repository.terminateOrCancelContract(contract, id);
    }

    public int verifyContractId(int id) {
        Contract verifyContract = repository.readOneContract(id);
        String status = verifyContract.getStatus();

        if(status.equals(ContractEnum.ENCERRADO.toString()) || status.equals(ContractEnum.CANCELADO.toString())) {
            throw new RuntimeException("Contrato não pode ser editado");
        }

        return verifyContract.getId();
    }
}
