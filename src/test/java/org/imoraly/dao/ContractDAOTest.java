package org.imoraly.dao;

import org.imoraly.model.Contract;
import org.junit.jupiter.api.*;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractDAOTest {

    private Connection conn;
    private ContractDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        conn = TestDatabaseFactory.createAllTable();
        dao = new ContractDAO(conn);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    @DisplayName("Criar contrato")
    void createContractTest() {
        Contract contract = new Contract();

        contract.setDescription("Criar um site simples");
        contract.setHourlyRate(10.00);
        contract.setContractedHours(4);
        contract.setTax(5.50);
        contract.setBonus(30.00);
        contract.setStatus("Pendente");
        contract.setFreelancerId(2);
        contract.setClientId(2);

        dao.createContract(contract);
        Assertions.assertNotNull(contract);
    }

    @Test
    @DisplayName("Ler os dados do banco")
    void readContractTest() {
        dao.readContract().forEach(System.out::println);
    }

    @Test
    @DisplayName("Ler um contrato por id")
    void readOneContractTest() {
        var teste = dao.readOneContract(1);
        System.out.println(teste);
        Assertions.assertNotNull(teste);
        assertEquals(1, teste.getId());
    }

    @Test
    @DisplayName("Atualizar contrato")
    void updateContractTest() {
        Contract contract = new Contract();

        contract.setDescription("Criar um site simples");
        contract.setHourlyRate(10.00);
        contract.setContractedHours(4);
        contract.setTax(5.50);
        contract.setBonus(30.00);
        contract.setStatus("Pendente");
        contract.setFreelancerId(1);
        contract.setClientId(1);

        dao.updateContract(contract, 1);

        Contract actualization = dao.readOneContract(1);
        System.out.println(actualization);

        assertEquals("Criar um site simples", actualization.getDescription());
        assertEquals(10.00, actualization.getHourlyRate());
        assertEquals(4, actualization.getContractedHours());
        assertEquals(5.50, actualization.getTax());
        assertEquals(30.00, actualization.getBonus());
        assertEquals("Pendente", actualization.getStatus());
        assertEquals(1, actualization.getFreelancerId());
        assertEquals(1, actualization.getClientId());

    }
}