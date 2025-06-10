package org.imoraly.model;

public class Contract {
    private int id;
    private String description;
    private double hourlyRate;
    private int contractedHours;
    private double tax;
    private double bonus;
    private String status;
    private int freelancerId;
    private int clientId;
    private Freelancer freelancer;
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getContractedHours() {
        return contractedHours;
    }

    public void setContractedHours(int contractedHours) {
        this.contractedHours = contractedHours;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        StringBuilder contract = new StringBuilder();

        contract.append("Contrato: ").append(id).append("\n");
        contract.append("Descrição: ").append(description).append("\n");
        contract.append("Valor hora: ").append(hourlyRate).append("\n");
        contract.append("Horas: ").append(contractedHours).append("\n");
        contract.append("Imposto: ").append(tax).append("\n");
        contract.append("Bonus: ").append(bonus).append("\n");
        contract.append("Status: ").append(status).append("\n\n");

        if (freelancer != null) {
            contract.append("Freelancer: ").append(freelancer.getId()).append("\n");
            contract.append("Nome: ").append(freelancer.getName()).append("\n");
            contract.append("Email: ").append(freelancer.getEmail()).append("\n");
            contract.append("Especialidade: ").append(freelancer.getSpecialty()).append("\n\n");

        } else {
            contract.append("Freelancer: nenhum freelancer associado\n");
        }

        if (client != null) {
            contract.append("Cliente: ").append(client.getId()).append("\n");
            contract.append("Nome: ").append(client.getName()).append("\n");
            contract.append("Telefone: ").append(client.getTelephone()).append("\n");
            contract.append("Email: ").append(client.getEmail()).append("\n");
        } else {
            contract.append("Cliente: nenhum cliente associado\n");
        }

        contract.append("-------------------------\n");

        return contract.toString();
    }
}
