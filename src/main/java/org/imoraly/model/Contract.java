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
        return String.format(
                "Contrato: %d\nDescrição: %s\nStatus: %s\n" +
                        "Freelancer: %d\nNome: %s\nEspecialidade: %s\n" +
                        "Cliente: %d\nNome: %s\n" +
                        "-----------------------------------\n",
                this.id, this.description, this.status,
                this.freelancer.getId(), this.freelancer.getName(), this.freelancer.getSpecialty(),
                this.client.getId(), this.client.getName()
        );
    }
}
