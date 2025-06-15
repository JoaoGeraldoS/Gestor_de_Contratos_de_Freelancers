package org.imoraly.service;

import org.imoraly.model.Client;
import org.imoraly.repository.IClientRepository;

public class ClientService {

    private final IClientRepository repository;

    public ClientService(IClientRepository repository) {
        this.repository = repository;
    }

    public void createClients(Client client) {
        repository.createClient(client);
    }

    public void readClient() {

        repository.readClients().stream()
                .filter(Client::isActive)
                .forEach(System.out::println);
    }

    public void readOnClient(int id) {
        System.out.println(repository.readOnClient(id));
    }

    public void deleteClient(int id) {
        repository.deleteClient(id);
    }

}
