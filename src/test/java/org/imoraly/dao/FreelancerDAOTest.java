package org.imoraly.dao;

import org.imoraly.model.Freelancer;
import org.junit.jupiter.api.*;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FreelancerDAOTest {

    private Connection conn;
    private FreelancerDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        conn = TestDatabaseFactory.createAllTable();
        dao = new FreelancerDAO(conn);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    @DisplayName("Criar freelancer ")
    void createFreelancerTest() {
        Freelancer freelancer = new Freelancer();

        freelancer.setName("Maria");
        freelancer.setEmail("maria@gmail.com");
        freelancer.setCpf("12223456789");
        freelancer.setSpecialty("Front end");

        dao.createFreelancer(freelancer);
        Assertions.assertNotNull(freelancer);
    }

    @Test
    @DisplayName("Verifica se todos os freelances estão retornando")
    void readFreelancersTest() {
        var freelancer = dao.readFreelances();
        Assertions.assertNotNull(freelancer);
    }

    @Test
    @DisplayName("Verifica se o retono do freelancer esta correto")
    void readOnFreelancerTest() {
        var freelancer = dao.readOnFreelancer(1);
        Assertions.assertNotNull(freelancer);
    }

    @Test
    @DisplayName("Tenta atualizar o freelancer")
    void updateFreelancerTest() {
        Freelancer freelancer = new Freelancer();

        freelancer.setName("Joao");
        freelancer.setEmail("joao@gmail.com");
        freelancer.setCpf("1232345434");
        freelancer.setSpecialty("Back end Java");

        dao.updateFreelancer(freelancer, 1);

        var actualization = dao.readOnFreelancer(1);

        assertEquals("Joao", actualization.getName());
        assertEquals("joao@gmail.com", actualization.getEmail());
        assertEquals("1232345434", actualization.getCpf());
        assertEquals("Back end Java", actualization.getSpecialty());
    }

    @Test
    @DisplayName("Deleta o freelancer logicamente")
    void deleteFreelancerTest() {
        dao.deleteFreelancer(1);
    }

    @Test
    @DisplayName("Busca freelancer por nome e verifica se não é null")
    void searchFreelancerTest() {
       var freelancer = dao.searchFreelancer("Maria");

       assertNotNull(freelancer);
    }
}