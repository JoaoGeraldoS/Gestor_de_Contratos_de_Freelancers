package org.imoraly.dao;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.model.Client;
import org.imoraly.model.Contract;
import org.imoraly.model.Freelancer;

import java.sql.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {

    private Connection conn;

    public ContractDAO(Connection conn) {
        this.conn = conn;
    }


    public void createContract(Contract contract) {
        try {
            String sql = "INSERT INTO contract (description, hourly_rate, contracted_hours, tax," +
                    "bonus, status, id_freelancer, id_client) VALUES (?,?,?,?,?,?,?,?)";

            if(conn != null) {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, contract.getDescription());
                statement.setDouble(2, contract.getHourlyRate());
                statement.setInt(3, contract.getContractedHours());
                statement.setDouble(4, contract.getTax());
                statement.setDouble(5, contract.getBonus());
                statement.setString(6, contract.getStatus());
                statement.setInt(7, contract.getFreelancerId());
                statement.setInt(8, contract.getClientId());
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    int generateId = resultSet.getInt(1);
                    contract.setId(generateId);
                }


            } else {
                throw new RuntimeException("Erro ao salvar dados");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conecatr", e);
        }
    }

    public List<Contract> readContract() {
        List<Contract> contracts = new ArrayList<>();

        try {
            String sql = "SELECT co.id as contract_id, co.description, co.status,\n" +
                    "f.id as freela_id, f.name, f.specialty, cl.id as client_id, cl.name as client_name FROM contract co\n" +
                    "join freelancer f on co.id_freelancer = f.id join client cl on co.id_client = cl.id;";


            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Freelancer freelancer = new Freelancer();
                freelancer.setId(resultSet.getInt("freela_id"));
                freelancer.setName(resultSet.getString("name"));
                freelancer.setSpecialty(resultSet.getString("specialty"));

                Client client = new Client();
                client.setId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("client_name"));

                Contract contract = new Contract();
                contract.setId(resultSet.getInt("contract_id"));
                contract.setDescription(resultSet.getString("description"));
                contract.setStatus(resultSet.getString("status"));
                contract.setFreelancer(freelancer);
                contract.setClient(client);

                contracts.add(contract);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar");
        }
        return contracts;
    }

}
