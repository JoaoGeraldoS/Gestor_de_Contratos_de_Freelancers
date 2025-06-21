package org.imoraly.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private BigDecimal imposto;
    private BigDecimal total;

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

    public BigDecimal getImposto() {
        return imposto;
    }

    public void setImposto(BigDecimal imposto) {
        this.imposto = imposto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void contractCalculator() {
        BigDecimal hourlyRateBD = BigDecimal.valueOf(hourlyRate);
        BigDecimal contractedHoursBD = BigDecimal.valueOf(contractedHours);
        BigDecimal bonusBD = BigDecimal.valueOf(bonus);
        BigDecimal taxPercent = BigDecimal.valueOf(tax).divide(BigDecimal.valueOf(100));
        BigDecimal subtotal = hourlyRateBD.multiply(contractedHoursBD).add(bonusBD);

        this.imposto = subtotal.multiply(taxPercent).setScale(2, RoundingMode.HALF_EVEN);

        this.total = subtotal.subtract(imposto).setScale(2, RoundingMode.HALF_EVEN);
    }

    public String generateReportContact() {
        StringBuilder report = new StringBuilder();

        report.append("========= CONTRATO =========\n");

        report.append("Descrição: ").append(description).append("\n");
        report.append("Status: ").append(status).append("\n");
        report.append("----------------------------\n");
        report.append("Valor hora: R$ ").append(String.format("%.2f", hourlyRate)).append("\n");
        report.append("Horas contratadas: ").append(contractedHours).append("\n");
        report.append("Bônus: R$ ").append(String.format("%.2f", bonus)).append("\n");
        report.append("Imposto: ").append(tax).append("%\n");
        report.append("Valor do Imposto: R$ ").append(imposto.setScale(2, RoundingMode.HALF_EVEN)).append("\n");
        report.append("Total do contrato: R$ ").append(total.setScale(2, RoundingMode.HALF_EVEN)).append("\n");
        report.append("----------------------------\n");

        if (freelancer != null) {
            report.append("FREELANCER\n");
            report.append("Nome: ").append(freelancer.getName()).append("\n");
            report.append("Email: ").append(freelancer.getEmail()).append("\n");
            report.append("Especialidade: ").append(freelancer.getSpecialty()).append("\n");
            report.append("----------------------------\n");
        }

        if (client != null) {
            report.append("CLIENTE\n");
            report.append("Nome: ").append(client.getName()).append("\n");
            report.append("Telefone: ").append(client.getTelephone()).append("\n");
            report.append("Email: ").append(client.getEmail()).append("\n");
            report.append("----------------------------\n");
        }

        return report.toString();
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
        contract.append("Status: ").append(status).append("\n");
        contract.append("Impostos: R$ ").append(imposto.setScale(2, RoundingMode.HALF_EVEN)).append("\n");
        contract.append("Total do Contrato (com imposto): R$ ").append(total.setScale(2, RoundingMode.HALF_EVEN)).append("\n\n");

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
