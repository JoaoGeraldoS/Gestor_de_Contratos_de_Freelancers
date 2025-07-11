package org.imoraly.model;

public class Freelancer {

    private int id;
    private String name;
    private String email;
    private String cpf;
    private String specialty;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder freelancer = new StringBuilder();

        freelancer.append("Id: ").append(id);
        freelancer.append("\nName: ").append(name);
        freelancer.append("\nEmail: ").append(email);
        freelancer.append("\nCpf: ").append(cpf);
        freelancer.append("\nEspecialidade: ").append(specialty).append("\n");

        if(!isActive()) {
            freelancer.append("Inativo\n");
        }
        return freelancer.toString();
    }
}


