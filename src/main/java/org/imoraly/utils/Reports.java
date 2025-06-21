package org.imoraly.utils;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.model.Client;
import org.imoraly.model.Contract;
import org.imoraly.model.Freelancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reports {

    public List<Contract> generateReport(String status) {
        List<Contract> contracts = new ArrayList<>();
        int count = 0;

        String sql = """ 
             SELECT ct.id, ct.description, ct.hourly_rate, ct.contracted_hours, ct.bonus, ct.tax, ct.status,
             c.id AS client_id, c.name AS client_name,c.telephone, c.email AS client_email, f.name AS freelancer_name,
             f.email AS freelancer_email, f.specialty
             FROM contract ct INNER JOIN client c ON ct.id_client = c.id INNER JOIN freelancer f ON ct.id_freelancer = f.id
             WHERE ct.status = ?;
        """;

        try (Connection conn = ConnectionDB.connect();){

            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contract contract = new Contract();

                contract.setDescription(resultSet.getString("description"));
                contract.setHourlyRate(resultSet.getDouble("hourly_rate"));
                contract.setContractedHours(resultSet.getInt("contracted_hours"));
                contract.setTax(resultSet.getDouble("tax"));
                contract.setBonus(resultSet.getDouble("bonus"));
                contract.setStatus(resultSet.getString("status"));

                if(!resultSet.wasNull()) {
                    Freelancer freelancer = new Freelancer();
                    freelancer.setName(resultSet.getString("freelancer_name"));
                    freelancer.setEmail(resultSet.getString("freelancer_email"));
                    freelancer.setSpecialty(resultSet.getString("specialty"));
                    contract.setFreelancer(freelancer);
                }

                if (!resultSet.wasNull()) {
                    Client client = new Client();

                    client.setName(resultSet.getString("client_name"));
                    client.setTelephone(resultSet.getString("telephone"));
                    client.setEmail(resultSet.getString("client_email"));
                    contract.setClient(client);
                }

                contract.contractCalculator();

                contracts.add(contract);

                count++;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar");
        }
        return contracts;
    }


    public void printReport(List<Contract> contracts) {
        System.out.println("==========================");
        System.out.println(" RELATÓRIO DE CONTRATOS ");
        System.out.println("==========================\n");

        contracts.forEach(contract -> {
            System.out.println(contract.generateReportContact());
        });

        System.out.println("==========================");
        System.out.println(" Total de Contratos: " + contracts.size());
        System.out.println("==========================\n");
    }

}
