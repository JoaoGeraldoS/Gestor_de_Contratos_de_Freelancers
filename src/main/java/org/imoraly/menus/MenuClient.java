package org.imoraly.menus;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.dao.ClientDAO;
import org.imoraly.model.Client;
import org.imoraly.repository.IClientRepository;
import org.imoraly.service.ClientService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuClient {
    private final Map<Integer, Runnable> options = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private final Client client = new Client();

    private final ClientService service;

    public MenuClient() {
        Connection conn = ConnectionDB.connect();
        IClientRepository repository =  new ClientDAO(conn);
        this.service = new ClientService(repository);

        options.put(1, this::addClient);
        options.put(2, this::readClients);
        options.put(3, this::readOnClient);
        options.put(4, this::updateClient);
        options.put(5, this::deleteClient);
    }

    public void menuClient() {
        int option;

        do {
            InterfaceMenus.interfaceClient();

            option = scanner.nextInt();
            scanner.nextLine();

            Runnable runnable = options.get(option);

            if(runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida");
            }
        } while (option != 0);
    }

    private void addClient() {
        System.out.print("Digite o nome do cliente: ");
        client.setName(scanner.nextLine());

        System.out.print("Digite o email do cliente: ");
        client.setEmail(scanner.nextLine());

        System.out.print("Digite o cpf/cnpj do cliente: ");
        client.setCnpjOrCpf(scanner.nextLine());

        System.out.print("Digite o telefone do cliente: ");
        client.setTelephone(scanner.nextLine());

        service.createClients(client);
    }

    private void readClients() {
        service.readClient();
    }

    private void  readOnClient(){
        System.out.print("Digite o id do cliente: ");
        Client readOnClient = service.readOnClient(scanner.nextInt());
        System.out.println(readOnClient);
    }

    private void updateClient() {
        System.out.print("Digite o id do cliente: ");
        Client readOnClient = service.readOnClient(scanner.nextInt());

        System.out.print("Digite o nome do cliente: ");
        client.setName(scanner.nextLine());

        System.out.print("Digite o cpf/cnpj do cliente: ");
        client.setCnpjOrCpf(scanner.nextLine());

        System.out.print("Digite o telefone do cliente: ");
        client.setTelephone(scanner.nextLine());

        System.out.print("Digite o email do cliente: ");
        client.setEmail(scanner.nextLine());

        service.updateClient(client, readOnClient.getId());
    }

    private void deleteClient() {
        System.out.print("Digite o id do cliente: ");
        service.deleteClient(scanner.nextInt());
    }
}
