package org.imoraly.repository;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.dao.ClientDAO;
import org.imoraly.model.Client;
import org.imoraly.model.Contract;

import java.util.List;

public interface IClientRepository {

    void createClient(Client client);
    List<Client> readClients();
    Client readOnClient(int id);
    void updateClient(Client client, int id);
    void deleteClient(int id);
}
