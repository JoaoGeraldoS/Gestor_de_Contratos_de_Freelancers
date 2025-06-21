package org.imoraly.dao;

import org.imoraly.model.Freelancer;
import org.imoraly.repository.IFreelancerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FreelancerDAO implements IFreelancerRepository {

    private final Connection conn; //Usado para testes

    public FreelancerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createFreelancer(Freelancer freelancer) {

        String sql = "INSERT INTO freelancer (name,email, cpf, specialty) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

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
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar", e);
        }
    }

    @Override
    public List<Freelancer> readFreelances() {
        List<Freelancer> freelancers = new ArrayList<>();

        String sql = "SELECT * FROM freelancer";

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                Freelancer freelancer = new Freelancer();

                freelancer.setId(resultSet.getInt("id"));
                freelancer.setName(resultSet.getString("name"));
                freelancer.setEmail(resultSet.getString("email"));
                freelancer.setCpf(resultSet.getString("cpf"));
                freelancer.setSpecialty(resultSet.getString("specialty"));
                freelancer.setActive(resultSet.getBoolean("active"));

                freelancers.add(freelancer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar freelancers", e);
        }
        return freelancers;
    }

    @Override
    public Freelancer readOnFreelancer(int id) {
        Freelancer freelancer = null;

        String sql = "SELECT * FROM freelancer WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    freelancer = new Freelancer();

                    freelancer.setId(resultSet.getInt("id"));
                    freelancer.setName(resultSet.getString("name"));
                    freelancer.setEmail(resultSet.getString("email"));
                    freelancer.setCpf(resultSet.getString("cpf"));
                    freelancer.setSpecialty(resultSet.getString("specialty"));
                    freelancer.setActive(resultSet.getBoolean("active"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar freelancers", e);
        }
        return freelancer;
    }

    @Override
    public void updateFreelancer(Freelancer freelancer, int id) {

        String sql = "UPDATE freelancer SET name = ?, email = ?, cpf = ?, specialty = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql);){

            statement.setString(1, freelancer.getName());
            statement.setString(2, freelancer.getEmail());
            statement.setString(3, freelancer.getCpf());
            statement.setString(4, freelancer.getSpecialty());
            statement.setInt(5, id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha afetada");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o freelancer", e);
        }
    }

    @Override
    public void deleteFreelancer(int id) {

        String sql = "UPDATE freelancer SET active = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql);){

            statement.setBoolean(1, false);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha afetada");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar freelancer", e);
        }
    }

    @Override
    public Freelancer searchFreelancer(String name) {
        String sql = "SELECT id, name FROM freelancer WHERE name = ?";
        Freelancer freelancer = null;

        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    freelancer = new Freelancer();

                    freelancer.setId(resultSet.getInt("id"));
                    freelancer.setName(resultSet.getString("name"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar freelancer: " + e.getMessage());
        }
        return freelancer;
    }
}
