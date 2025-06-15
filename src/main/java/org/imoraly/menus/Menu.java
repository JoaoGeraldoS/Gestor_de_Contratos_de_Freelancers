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

public class Menu {
    private final Map<Integer, Runnable> opcoes = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private final Client client = new Client();

    private final ClientService service;

    public Menu() {
        Connection conn = ConnectionDB.connect();
        IClientRepository repository =  new ClientDAO(conn);
        this.service = new ClientService(repository);

        opcoes.put(1, this::addClient);
        opcoes.put(2, this::readClients);
        opcoes.put(3, this::readOnClient);
        opcoes.put(4, this::deleteClient);
    }

    public void menuClient() {
        int opcao;

        do {
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            Runnable runnable = opcoes.get(opcao);

            if(runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida");
            }
        } while (opcao != 0);
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

    public void  readOnClient(){
        System.out.print("Digite o id do cliente: ");
        service.readOnClient(scanner.nextInt());
    }


    private void deleteClient() {
        System.out.print("Digite o id do cliente: ");
        service.deleteClient(scanner.nextInt());
    }

}
