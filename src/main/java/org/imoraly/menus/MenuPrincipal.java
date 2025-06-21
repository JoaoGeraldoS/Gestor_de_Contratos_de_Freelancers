package org.imoraly.menus;

import org.imoraly.model.Contract;
import org.imoraly.utils.Reports;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private final static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal() {
        while (true) {
            switch (menuInicial()) {
                case 1 -> {
                    System.out.println("\n====> Menu Freelancer <====\n");
                    MenuFreelancer menuFreelancer = new MenuFreelancer();
                    menuFreelancer.menuFreelancer();
                }
                case 2 -> {
                    System.out.println("\n====> Menu Cliente <====\n");
                    MenuClient menuClient = new MenuClient();
                    menuClient.menuClient();
                }
                case 3 -> {
                    System.out.println("\n====> Menu Contrato <====\n");
                    MenuContract menuContract = new MenuContract();
                    menuContract.menuContract();
                }
                case 4 -> {
                    System.out.println("\n===== MENU RELATÓRIO =====\n");
                    MenuReports menuReports = new MenuReports();
                    menuReports.menuReports();

                }
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Entrada invalida");
            }
        }
    }

    private static int menuInicial() {
        System.out.println("\n====> Menu Principal <====\n");

        System.out.println("1 - Freelancers");
        System.out.println("2 - Clientes");
        System.out.println("3 - Contratos");
        System.out.println("4 - Relatórios");
        System.out.println("0 - Sair");

        System.out.print("Digite uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        return opcao;
    }

}
