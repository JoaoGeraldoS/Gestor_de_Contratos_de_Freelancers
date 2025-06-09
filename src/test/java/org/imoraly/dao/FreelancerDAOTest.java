package org.imoraly.dao;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.model.Freelancer;
import org.junit.jupiter.api.*;
import org.sqlite.SQLiteException;
import utils.TestDatabaseFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
    void createFreelancer() {
        Freelancer freelancer = new Freelancer();

        freelancer.setName("Maria");
        freelancer.setEmail("maria@gmail.com");
        freelancer.setCpf("12223456789");
        freelancer.setSpecialty("Front end");

        dao.createFreelancer(freelancer);
        Assertions.assertNotNull(freelancer);

    }


}