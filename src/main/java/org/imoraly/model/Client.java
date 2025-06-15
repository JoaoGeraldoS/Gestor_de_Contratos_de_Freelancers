package org.imoraly.model;

public class Client {
    private int id;
    private String name;
    private String cnpjOrCpf;
    private String email;
    private String telephone;
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

    public String getCnpjOrCpf() {
        return cnpjOrCpf;
    }

    public void setCnpjOrCpf(String cnpjOrCpf) {
        this.cnpjOrCpf = cnpjOrCpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder client = new StringBuilder();

        client.append("id: ").append(id).append("\n");
        client.append("Nome: ").append(name).append("\n");
        client.append("Email: ").append(email).append("\n");
        client.append("Cpf/Cnpj: ").append(cnpjOrCpf).append("\n");
        client.append("Telefone: ").append(telephone).append("\n");

        if(!isActive()) {
            client.append("Inativo").append("\n");
        }

        return client.toString();
    }

}
