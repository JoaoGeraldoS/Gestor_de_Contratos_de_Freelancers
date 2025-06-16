package org.imoraly.service;

import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IContractRepository;

public class ContractService {

    private IContractRepository repository;

    public void createContract(Contract contract) {
        if(contract.getContractedHours() <= 0) {
            throw new RuntimeException("A hora deve ser maior que 0");
        }

        Contract freela = repository.readContractForFreelancerAndClient(contract.getFreelancerId(), contract.getClientId());

        if (freela.getStatus().equals(ContractEnum.ATIVO.toString())) {
            throw new RuntimeException("Ja existe um contrato ativo entre as partes");
        }

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
        Contract verifyStatus = repository.readOneContract(id);
        String status = verifyStatus.getStatus();

        if(status.equals(ContractEnum.ENCERRADO.toString()) || status.equals(ContractEnum.CANCELADO.toString())) {
            throw new RuntimeException("Contrato não pode ser editado");
        }

        repository.updateContract(contract, id);
    }

}
