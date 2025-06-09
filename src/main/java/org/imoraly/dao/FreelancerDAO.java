package org.imoraly.dao;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.model.Freelancer;

import java.sql.*;

public class FreelancerDAO {

    private final Connection conn; //Usado para testes

    public FreelancerDAO(Connection conn) {
        this.conn = conn;
    }


    public void createFreelancer(Freelancer freelancer) {
        try {
            String sql = "INSERT INTO freelancer (name,email, cpf, specialty) VALUES (?, ?, ?, ?)";

            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, freelancer.getName());
                statement.setString(2, freelancer.getEmail());
                statement.setString(3, freelancer.getCpf());
                statement.setString(4, freelancer.getSpecialty());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generateId = resultSet.getInt(1);
                    freelancer.setId(generateId);
                }

            } else {
                throw new RuntimeException("Erro ao salvar dados");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar", e);
        }
    }

}
