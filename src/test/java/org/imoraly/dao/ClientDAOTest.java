package org.imoraly.dao;

import org.imoraly.model.Client;
import org.junit.jupiter.api.*;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void createClientTest() {
        Client client = new Client();

        client.setName("Abraão");
        client.setCnpjOrCpf("1233345356");
        client.setEmail("abraao@gmail.com");
        client.setTelephone("123456378");

        dao.createClient(client);
        Assertions.assertNotNull(client);
    }

    @Test
    @DisplayName("Verifica se todos os clientes estão retornando")
    void readClinetsTest() {
        var client = dao.readClients();
        Assertions.assertNotNull(client);
    }

    @Test
    @DisplayName("Verifica se o retono do clente esta correto")
    void readOnClientTest(){
        var client = dao.readOnClient(1);
        Assertions.assertNotNull(client);
    }

    @Test
    @DisplayName("Tenta atualizar o cliente")
    void updateClientTest() {
        Client client = new Client();

        client.setName("Abraão");
        client.setCnpjOrCpf("1233345356");
        client.setEmail("abraao@gmail.com");
        client.setTelephone("123456378");

        dao.updateClient(client, 1);

        Client actualization = dao.readOnClient(1);

        assertEquals("Abraão", actualization.getName());
        assertEquals("1233345356", actualization.getCnpjOrCpf());
        assertEquals("abraao@gmail.com", actualization.getEmail());
        assertEquals("123456378", actualization.getTelephone());

    }

    @Test
    @DisplayName("Deleta o clinte logicamente")
    void deleteClientTest() {
        dao.deleteClient(1);
    }

    @Test
    @DisplayName("Busca client por nome e verifica se não é null")
    void searchClientTest(){
        var client = dao.searchClient("João");
        assertNotNull(client);
    }
}