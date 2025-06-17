package org.imoraly.menus;

import org.imoraly.conection.ConnectionDB;
import org.imoraly.dao.FreelancerDAO;
import org.imoraly.model.Freelancer;
import org.imoraly.repository.IFreelancerRepository;
import org.imoraly.service.FreelancerService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuFreelancer {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, Runnable> opcoes = new HashMap<>();

    private final Freelancer freelancer = new Freelancer();
    private final FreelancerService service;

    public MenuFreelancer() {
        Connection conn = ConnectionDB.connect();
        IFreelancerRepository repository = new FreelancerDAO(conn);
        this.service = new FreelancerService(repository);

        opcoes.put(1, this::createFreelancer);
        opcoes.put(2, this::readAllFreelancers);
        opcoes.put(3, this::readOneFreelancer);
        opcoes.put(4, this::deleteFreelancer);

    }

    public void menuFreelancer() {
        int opcao;

        do {
            System.out.print("Digite a opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            Runnable runnable = opcoes.get(opcao);
            if (runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida!");
            }
        } while (opcao != 0);
    }


    private void createFreelancer() {
        System.out.print("Digite o nome do freelancer: ");
        freelancer.setName(scanner.nextLine());

        System.out.print("Digite o email do freelancer: ");
        freelancer.setEmail(scanner.nextLine());

        System.out.print("Digite o cpf do freelancer: ");
        freelancer.setCpf(scanner.nextLine());

        System.out.print("Digite a especialidade do freelancer: ");
        freelancer.setSpecialty(scanner.nextLine());

        service.createFreelancer(freelancer);
    }

    private void readAllFreelancers() {
        service.readAllFreelancer();
    }

    private void readOneFreelancer() {
        System.out.print("Digite o id co freelancer: ");
        service.readOneFreelancer(scanner.nextInt());
    }

    private void deleteFreelancer() {
        System.out.print("Digite o id do freelancer: ");
        service.deleteFreelancer(scanner.nextInt());
    }

}
