package org.imoraly.service;

import org.imoraly.dao.ContractDAO;
import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ContractServiceTest {

    @Mock
    private IContractRepository repository;

    @InjectMocks
    private ContractService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createContractTest() {
        Contract contract = new Contract();

        contract.setDescription("Criar um site simples");
        contract.setHourlyRate(10.00);
        contract.setContractedHours(10);
        contract.setTax(5.50);
        contract.setBonus(30.00);
        contract.setFreelancerId(1);
        contract.setClientId(1);

        System.out.println("Horas contratadas: " + contract.getContractedHours());


        Contract contractExist = new Contract();
        contractExist.setStatus(ContractEnum.ENCERRADO.toString());

        when(repository.readContractForFreelancerAndClient(1,1)).thenReturn(contractExist);
        doNothing().when(repository).createContract(any(Contract.class));

        service.createContract(contract);

        verify(repository, times(1)).createContract(contract);

    }
}