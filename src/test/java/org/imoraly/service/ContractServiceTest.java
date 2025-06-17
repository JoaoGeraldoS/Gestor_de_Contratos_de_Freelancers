package org.imoraly.service;

import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IContractRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ContractServiceTest {

    @Mock
    private IContractRepository repository;

    @InjectMocks
    private ContractService service;

    @Test
    @DisplayName("Verifica se a service esta buscando o contrato e salvando corretamente")
    void createContractTest() {
        Contract contract = new Contract();

        contract.setContractedHours(10);

        contract.setFreelancerId(1);
        contract.setClientId(1);

        Contract contractExist = new Contract();
        contractExist.setStatus(ContractEnum.ENCERRADO.toString());

        when(repository.readContractForFreelancerAndClient(1,1)).thenReturn(contractExist);
        doNothing().when(repository).createContract(any(Contract.class));

        service.createContract(contract);

        verify(repository, times(1)).createContract(contract);
    }

    @Test
    @DisplayName("Verifica se não foi execultado outra linha apos os testes")
    void createContractNotTest() {
        Contract contract = new Contract();

        contract.setContractedHours(0);

        assertEquals(0, contract.getContractedHours());

        contract.setFreelancerId(1);
        contract.setClientId(1);

        when(repository.readContractForFreelancerAndClient(1, 1)).thenReturn(contract);

        assertThrows(RuntimeException.class, () -> service.createContract(contract));

        verify(repository, never()).readContractForFreelancerAndClient(1, 1);
    }

    @Test
    @DisplayName("Realizar verificação de erros na hora de atualizar um contrato com status Encerrado ou Cancelado")
    void updateContractNotActivateTest() {
        Contract contract = new Contract();

        contract.setStatus(ContractEnum.ENCERRADO.toString());
        when(repository.readOneContract(1)).thenReturn(contract);

        assertThrows(RuntimeException.class, () -> service.updateContract(contract, 1));
        verify(repository, never()).updateContract(contract, 1);
    }

    @Test
    @DisplayName("Atualiza o contrato se ele ainda estiver ativo")
    void updateContractActivateTest() {
        Contract contract = new Contract();

        contract.setContractedHours(5);

        contract.setFreelancerId(1);
        contract.setClientId(1);

        Contract contractExist = new Contract();
        contractExist.setStatus(ContractEnum.ATIVO.toString());

        when(repository.readOneContract(1)).thenReturn(contractExist);
        doNothing().when(repository).updateContract(any(), anyInt());

       service.updateContract(contract, 1);

       verify(repository, times(1)).updateContract(contract, 1);
    }

    @Test
    void readContractsTest() {
       service.readContracts();
       verify(repository, times(1)).readContract();
    }

    @Test
    void readOneContractNullTest() {
        var contract = service.readOneContract(1);
        Assertions.assertNull(contract);
    }
}