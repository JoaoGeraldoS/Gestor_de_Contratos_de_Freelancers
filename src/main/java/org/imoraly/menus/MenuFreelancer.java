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

    private final  Scanner scanner = new Scanner(System.in);
    private final  Map<Integer, Runnable> options = new HashMap<>();

    private final Freelancer freelancer = new Freelancer();
    private final FreelancerService service;

    public MenuFreelancer() {
        Connection conn = ConnectionDB.connect();
        IFreelancerRepository repository = new FreelancerDAO(conn);
        this.service = new FreelancerService(repository);

        options.put(1, this::createFreelancer);
        options.put(2, this::readAllFreelancers);
        options.put(3, this::readOneFreelancer);
        options.put(4, this::updateFreelancer);
        options.put(5, this::deleteFreelancer);
    }

    public  void  menuFreelancer() {
        int option;

        do {
            InterfaceMenus.interfaceFreelancer();

            option = scanner.nextInt();
            scanner.nextLine();

            Runnable runnable = options.get(option);
            if (runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida!");
            }
        } while (option != 0);
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
        Freelancer freela = service.readOneFreelancer(scanner.nextInt());
        System.out.println(freela);
    }

    private void updateFreelancer() {
        System.out.print("Digite o id do freelancer: ");
        var id = service.readOneFreelancer(scanner.nextInt());

        System.out.print("Digite o nome: ");
        freelancer.setName(scanner.nextLine());

        System.out.print("Digite o email do freelancer: ");
        freelancer.setEmail(scanner.nextLine());

        System.out.print("Digite o cpf do freelancer: ");
        freelancer.setCpf(scanner.nextLine());

        System.out.print("Digite a especialidade do freelancer: ");
        freelancer.setSpecialty(scanner.nextLine());

        service.updateFreelancer(freelancer, id.getId());
    }

    private void deleteFreelancer() {
        System.out.print("Digite o id do freelancer: ");
        var id = service.readOneFreelancer(scanner.nextInt());
        service.deleteFreelancer(id.getId());
    }
}
