package org.imoraly.dao;

import org.imoraly.model.Contract;
import org.junit.jupiter.api.*;
import org.sqlite.SQLiteException;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}