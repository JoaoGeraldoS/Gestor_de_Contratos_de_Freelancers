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

    public Client readOnClient(int id) {
       Client client = repository.readOnClient(id);

        if(client == null){
            throw new RuntimeException("Cliente não existe");
        }

        return client;
    }

    public void updateClient(Client client, int id) {
        repository.updateClient(client, id);
    }

    public void deleteClient(int id) {
        repository.deleteClient(id);
    }

    public int searchClient(String name){
        Client client = repository.searchClient(name);

        if(client == null){
            throw new RuntimeException("Cliente não existe");
        }

        return client.getId();
    }

}
