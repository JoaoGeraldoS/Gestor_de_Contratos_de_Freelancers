package org.imoraly.service;

import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IContractRepository;

public class ContractService {

    private IContractRepository repository;

    public void createContract(Contract contract) {
        if(contract.getContractedHours() < 1) {
            throw new RuntimeException("A hora deve ser maior que 0");
        }

        Contract freela = repository.readContractForFreelancerAndClient(contract.getFreelancerId(), contract.getClientId());

        if (freela.getStatus().equals(ContractEnum.ATIVO.toString())) {
            throw new RuntimeException("Ja existe um contrato ativo entre as partes");
        }

        repository.createContract(contract);
    }
}
