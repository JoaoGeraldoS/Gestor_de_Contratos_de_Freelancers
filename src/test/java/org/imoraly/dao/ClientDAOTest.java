package org.imoraly.dao;

import org.imoraly.model.Client;
import org.junit.jupiter.api.*;
import org.sqlite.SQLiteException;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {

    private Connection conn;
    private ClientDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        conn = TestDatabaseFactory.createAllTable();
        dao = new ClientDAO(conn);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    @DisplayName("Criar cliente")
    void createClient() {
        Client client = new Client();

        client.setName("Abraão");
        client.setCnpjOrCpf("1233345356");
        client.setEmail("abraao@gmail.com");
        client.setTelephone("123456378");

        dao.createClient(client);
        Assertions.assertNotNull(client);
    }
}