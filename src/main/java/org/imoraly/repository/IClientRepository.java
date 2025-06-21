package org.imoraly.repository;

import org.imoraly.model.Client;

import java.util.List;

public interface IClientRepository {

    void createClient(Client client);
    List<Client> readClients();
    Client readOnClient(int id);
    void updateClient(Client client, int id);
    void deleteClient(int id);
    Client searchClient(String name);
}
