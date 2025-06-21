package org.imoraly.menus;

import org.imoraly.enums.ContractEnum;
import org.imoraly.model.Contract;
import org.imoraly.utils.Reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuReports {
    private final Map<Integer, Runnable> options = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Reports reports = new Reports();

    public MenuReports() {
        options.put(1, this::generateReportContract);
    }

    public void menuReports() {
        int option;

        do {
            System.out.println("1 - Gerar relatório de contratos");
            System.out.println("0 - Voltar");

            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            Runnable runnable = options.get(option);
            if(runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida");
            }
        } while (option != 0);
    }

    private void generateReportContract() {
        InterfaceMenus.interfaceReports();

        String status = "";

        switch (scanner.nextInt()) {
            case 1 -> status = ContractEnum.ATIVO.toString();
            case 2 -> status = ContractEnum.ENCERRADO.toString();
            case 3 -> status = ContractEnum.CANCELADO.toString();
        }

        List<Contract> contracts = reports.generateReport(status);

        if (contracts.isEmpty()) {
            System.out.printf("Nenhum contrato %s encontrado.\n\n", status.toLowerCase());
        } else {
            reports.printReport(contracts);
        }
    }
}
