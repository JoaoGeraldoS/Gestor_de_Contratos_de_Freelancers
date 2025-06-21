package org.imoraly.menus;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.dao.ClientDAO;
import org.imoraly.dao.ContractDAO;
import org.imoraly.dao.FreelancerDAO;
import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.repository.IClientRepository;
import org.imoraly.repository.IContractRepository;
import org.imoraly.repository.IFreelancerRepository;
import org.imoraly.service.ClientService;
import org.imoraly.service.ContractService;
import org.imoraly.service.FreelancerService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuContract {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, Runnable> opcoes = new HashMap<>();

    private final Contract contract = new Contract();
    private final ContractService contractService;

    private final FreelancerService freelancerService;
    private final ClientService clientService;

    public MenuContract(){
        Connection conn = ConnectionDB.connect();
        IContractRepository repository = new ContractDAO(conn);
        this.contractService = new ContractService(repository);

        IFreelancerRepository freelancerRepository = new FreelancerDAO(conn);
        this.freelancerService = new FreelancerService(freelancerRepository);

        IClientRepository clientRepository = new ClientDAO(conn);
        this.clientService = new ClientService(clientRepository);

        opcoes.put(1, this::createContract);
        opcoes.put(2, this::readAllContracts);
        opcoes.put(3, this::readOnContract);
        opcoes.put(4, this::updateContract);
        opcoes.put(5, this::terminateOrCancelContract);
    }

    public void menuContract() {
        int opcao;

        do {
            InterfaceMenus.interfaceContract();

            opcao = scanner.nextInt();
            scanner.nextLine();

            Runnable runnable = opcoes.get(opcao);
            if(runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida!");
            }
        } while (opcao != 0);
    }

    private void createContract(){
        System.out.print("Digite o nome do freelancer: ");
        int freelancerId = freelancerService.searchFreelancer(scanner.nextLine());
        contract.setFreelancerId(freelancerId);

        System.out.print("Digite o nome do cliente: ");
        int clientID = clientService.searchClient(scanner.nextLine());
        contract.setClientId(clientID);

        contractService.verifyContractFreelancerAndClient(freelancerId, clientID);

        System.out.print("Digite a descrição do contrato: ");
        contract.setDescription(scanner.nextLine());

        System.out.println("Digite as horas trabalhadas: ");
        contract.setHourlyRate(scanner.nextDouble());

        System.out.println("Digite a quantidade de horas contratadas: ");
        contract.setContractedHours(scanner.nextInt());

        System.out.println("Digite a porcentagem da taxa (somente o valor): ");
        contract.setTax(scanner.nextDouble());

        System.out.println("Digite o valor do bonus: ");
        contract.setBonus(scanner.nextDouble());

        contractService.createContract(contract);
    }

    private void readAllContracts() {
        contractService.readContracts();
    }

    private void readOnContract() {
        System.out.print("Digite o id do contrato: ");
        var contract = contractService.readOneContract(scanner.nextInt());
        System.out.println(contract);
    }

    private void updateContract() {
        System.out.print("Digite o id do contrato:");
        var id = contractService.verifyContractId(scanner.nextInt());

        System.out.print("Digite a descrição do contrato: ");
        contract.setDescription(scanner.nextLine());

        System.out.println("Digite as horas trabalhadas: ");
        contract.setHourlyRate(scanner.nextDouble());

        System.out.println("Digite a quantidade de horas contratadas: ");
        contract.setContractedHours(scanner.nextInt());

        System.out.println("Digite a porcentagem da taxa (somente o valor): ");
        contract.setTax(scanner.nextDouble());

        System.out.println("Digite o valor do bonus: ");
        contract.setBonus(scanner.nextDouble());

        System.out.print("Digite o nome do freelancer: ");
        int freelancerId = freelancerService.searchFreelancer(scanner.nextLine());
        contract.setFreelancerId(freelancerId);

        System.out.print("Digite o nome do cliente: ");
        int clientID = clientService.searchClient(scanner.nextLine());
        contract.setClientId(clientID);

        contractService.updateContract(contract, id);
    }

    private void terminateOrCancelContract() {
        System.out.print("Digite o id do contrato: ");
        var id = contractService.verifyContractId(scanner.nextInt());

        System.out.print("Digite a opção: 1 - Encerrar, 2 - Cancelar: ");

        switch (scanner.nextInt()) {
            case 1 -> contract.setStatus(ContractEnum.ENCERRADO.toString());
            case 2 -> contract.setStatus(ContractEnum.CANCELADO.toString());
            default -> System.out.println("Entrada invalida!");
        }
        contractService.terminateOrCancelContract(contract, id);
    }
}
