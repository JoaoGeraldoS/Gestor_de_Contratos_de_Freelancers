package org.imoraly.dao;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.model.Client;

import java.sql.*;

public class ClientDAO {

    private Connection conn; // Usado para os testes

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

    public void createClient(Client client)  {
        try{

            String sql = "INSERT INTO client (name, cnpj_cpf, email, telephone) VALUES (?, ?, ?, ?)";

            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, client.getName());
                statement.setString(2, client.getCnpjOrCpf());
                statement.setString(3, client.getEmail());
                statement.setString(4, client.getTelephone());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    int genereteId = resultSet.getInt(1);
                    client.setId(genereteId);
                }
            } else {
                throw new RuntimeException("Erro ao salvar informações");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar", e);
        }
    }



}
