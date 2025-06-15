package org.imoraly.dao;

import org.imoraly.model.Client;
import org.imoraly.repository.IClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientRepository {

    private Connection conn; // Usado para os testes

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createClient(Client client)  {
        String sql = "INSERT INTO client (name, cnpj_cpf, email, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            if (conn != null) {
                statement.setString(1, client.getName());
                statement.setString(2, client.getCnpjOrCpf());
                statement.setString(3, client.getEmail());
                statement.setString(4, client.getTelephone());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    int generateId = resultSet.getInt(1);
                    client.setId(generateId);
                }
            } else {
                throw new RuntimeException("Erro ao salvar informações");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar", e);
        }
    }

    @Override
    public List<Client> readClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();

                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setCnpjOrCpf(resultSet.getString("cnpj_cpf"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setActive(resultSet.getBoolean("active"));
                clients.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar clientes");
        }
        return clients;
    }

    @Override
    public Client readOnClient(int id) {
        Client client = null;

        String sql = "SELECT * FROM client WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client();

                    client.setId(resultSet.getInt("id"));
                    client.setName(resultSet.getString("name"));
                    client.setEmail(resultSet.getString("email"));
                    client.setCnpjOrCpf(resultSet.getString("cnpj_cpf"));
                    client.setTelephone(resultSet.getString("telephone"));
                    client.setActive(resultSet.getBoolean("active"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return client;
    }

    @Override
    public void updateClient(Client client, int id) {

        String sql = "UPDATE client SET name = ?, cnpj_cpf = ?, email = ?, telephone = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getCnpjOrCpf());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getTelephone());
            statement.setInt(5, id);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha afetada");
            }

        } catch (SQLException e) {
            throw new RuntimeException("erro ao atualizar cliente", e);
        }
    }

    @Override
    public void deleteClient(int id) {

        String sql = "UPDATE client SET active = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setBoolean(1, false);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha afetada");
            }
        } catch (Exception e) {
            throw new RuntimeException("erro ao deletar cliente", e);
        }
    }
}
