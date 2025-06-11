package org.imoraly.repository;

import org.imoraly.model.Contract;

import java.util.List;

public interface IContractRepository {
    void createContract(Contract contract);
    List<Contract> readContract();
    Contract readOneContract(int id);
    void updateContract(Contract contract, int id);
    Contract readContractForFreelancerAndClient(int id_freela, int id_client);
}
