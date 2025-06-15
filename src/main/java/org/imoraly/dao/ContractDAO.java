package org.imoraly.dao;

import org.imoraly.model.Client;
import org.imoraly.model.Contract;
import org.imoraly.model.Freelancer;
import org.imoraly.repository.IContractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO implements IContractRepository {

    private Connection conn;

    public ContractDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createContract(Contract contract) {
        String sql = "INSERT INTO contract (description, hourly_rate, contracted_hours, tax," +
                "bonus, status, id_freelancer, id_client) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            if(conn != null) {
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

    @Override
    public List<Contract> readContract() {
        List<Contract> contracts = new ArrayList<>();

        String sql = "SELECT co.id as contract_id, co.description,co.hourly_rate,co.contracted_hours,co.tax,co.bonus, " +
                "co.status, co.id_freelancer as co_id_freela, co.id_client as co_id_client, " +
                "f.id as freela_id, f.name,f.email as freela_email, f.specialty, cl.id as client_id, cl.name as client_name, cl.telephone," +
                "cl.email as client_email FROM contract co " +
                "left join freelancer f on co.id_freelancer = f.id left join client cl on co.id_client = cl.id;";

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Contract contract = new Contract();

                contract.setId(resultSet.getInt("contract_id"));
                contract.setDescription(resultSet.getString("description"));
                contract.setHourlyRate(resultSet.getDouble("hourly_rate"));
                contract.setContractedHours(resultSet.getInt("contracted_hours"));
                contract.setTax(resultSet.getDouble("tax"));
                contract.setBonus(resultSet.getDouble("bonus"));
                contract.setStatus(resultSet.getString("status"));
                contract.setFreelancerId(resultSet.getInt("co_id_freela"));
                contract.setClientId(resultSet.getInt("co_id_client"));


                int idFreela = resultSet.getInt("freela_id");

                if(!resultSet.wasNull()) {
                    Freelancer freelancer = new Freelancer();
                    freelancer.setId(idFreela);
                    freelancer.setName(resultSet.getString("name"));
                    freelancer.setEmail(resultSet.getString("freela_email"));
                    freelancer.setSpecialty(resultSet.getString("specialty"));
                    contract.setFreelancer(freelancer);
                }

                int idClient = resultSet.getInt("client_id");

                if (!resultSet.wasNull()) {
                    Client client = new Client();
                    client.setId(idClient);
                    client.setName(resultSet.getString("client_name"));
                    client.setTelephone(resultSet.getString("telephone"));
                    client.setEmail(resultSet.getString("client_email"));
                    contract.setClient(client);
                }

                contracts.add(contract);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar");
        }
        return contracts;
    }

    @Override
    public Contract readOneContract(int id) {
        Contract contract = null;

        String sql = "SELECT co.id as contract_id, co.description,co.hourly_rate,co.contracted_hours,co.tax,co.bonus, " +
                "co.status, co.id_freelancer as co_id_freela, co.id_client as co_id_client, " +
                "f.id as freela_id, f.name,f.email as freela_email, f.specialty, cl.id as client_id, cl.name as client_name, cl.telephone," +
                "cl.email as client_email FROM contract co " +
                "left join freelancer f on co.id_freelancer = f.id left join client cl on co.id_client = cl.id WHERE co.id = ?;";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

           try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                   contract = new Contract();

                   contract.setId(resultSet.getInt("contract_id"));
                   contract.setDescription(resultSet.getString("description"));
                   contract.setHourlyRate(resultSet.getDouble("hourly_rate"));
                   contract.setContractedHours(resultSet.getInt("contracted_hours"));
                   contract.setTax(resultSet.getDouble("tax"));
                   contract.setBonus(resultSet.getDouble("bonus"));
                   contract.setStatus(resultSet.getString("status"));
                   contract.setFreelancerId(resultSet.getInt("co_id_freela"));
                   contract.setClientId(resultSet.getInt("co_id_client"));


                   int idFreela = resultSet.getInt("freela_id");

                   if (!resultSet.wasNull()) {
                       Freelancer freelancer = new Freelancer();
                       freelancer.setId(idFreela);
                       freelancer.setName(resultSet.getString("name"));
                       freelancer.setEmail(resultSet.getString("freela_email"));
                       freelancer.setSpecialty(resultSet.getString("specialty"));
                       contract.setFreelancer(freelancer);
                   }

                   int idClient = resultSet.getInt("client_id");

                   if (!resultSet.wasNull()) {
                       Client client = new Client();
                       client.setId(idClient);
                       client.setName(resultSet.getString("client_name"));
                       client.setTelephone(resultSet.getString("telephone"));
                       client.setEmail(resultSet.getString("client_email"));
                       contract.setClient(client);
                   }

               }

           }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar");
        }
        return contract;
    }

    @Override
    public void updateContract(Contract contract, int id) {

        String sql = " UPDATE contract SET description = ?, hourly_rate = ? , contracted_hours = ?, tax = ?," +
                " bonus = ?, status = ?, id_freelancer = ?, id_client = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)){



            statement.setString(1, contract.getDescription());
            statement.setDouble(2, contract.getHourlyRate());
            statement.setInt(3, contract.getContractedHours());
            statement.setDouble(4, contract.getTax());
            statement.setDouble(5, contract.getBonus());
            statement.setString(6, contract.getStatus());
            statement.setInt(7, contract.getFreelancerId());
            statement.setInt(8, contract.getClientId());
            statement.setInt(9, id);


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhum contrato foi atualizado. Verifique se o ID existe.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar!");
        }
    }

    @Override
    public Contract readContractForFreelancerAndClient(int id_freela, int id_client) {
        Contract contract = null;

        String sql = "SELECT * FROM contract c LEFT JOIN freelancer f ON c.id_freelancer = f.id " +
                "LEFT JOIN client cl ON c.id_client = cl.id WHERE f.id = ? and cl.id = ?;";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id_freela);
            statement.setInt(2, id_client);

            try (ResultSet resultSet = statement.executeQuery()) {

                if(resultSet.next()) {
                    contract = new Contract();

                    contract.setId(resultSet.getInt("id"));
                    contract.setDescription(resultSet.getString("description"));
                    contract.setHourlyRate(resultSet.getDouble("hourly_rate"));
                    contract.setContractedHours(resultSet.getInt("contracted_hours"));
                    contract.setTax(resultSet.getDouble("tax"));
                    contract.setBonus(resultSet.getDouble("bonus"));
                    contract.setStatus(resultSet.getString("status"));
                    contract.setFreelancerId(resultSet.getInt("id_freelancer"));
                    contract.setClientId(resultSet.getInt("id_client"));

                    int idFreela = resultSet.getInt("id");

                    if(!resultSet.wasNull()) {
                        Freelancer freelancer = new Freelancer();
                        freelancer.setId(idFreela);
                        freelancer.setName(resultSet.getString("name"));
                        freelancer.setEmail(resultSet.getString("email"));
                        freelancer.setSpecialty(resultSet.getString("specialty"));
                        contract.setFreelancer(freelancer);
                    }

                    int idClient = resultSet.getInt("id");

                    if (!resultSet.wasNull()) {
                        Client client = new Client();
                        client.setId(idClient);
                        client.setName(resultSet.getString("name"));
                        client.setTelephone(resultSet.getString("telephone"));
                        client.setEmail(resultSet.getString("email"));
                        contract.setClient(client);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contract;
    }
}
