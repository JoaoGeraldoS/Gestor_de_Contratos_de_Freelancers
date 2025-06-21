package org.imoraly.menus;

public class InterfaceMenus {

    public static void interfaceFreelancer() {
        System.out.println("1 - Cadastrar freelancer");
        System.out.println("2 - Listar todos freelancers");
        System.out.println("3 - Listar um freelancer");
        System.out.println("4 - Atualizar um freelancer");
        System.out.println("5 - Apagar um freelancer");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    public static void interfaceClient() {
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar todos clientes");
        System.out.println("3 - Listar um cliente");
        System.out.println("4 - Atualizar um cliente");
        System.out.println("5 - Apagar um cliente");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    public static void interfaceContract() {
        System.out.println("1 - Criar contrato");
        System.out.println("2 - Listar contratos");
        System.out.println("3 - Listar um contrato");
        System.out.println("4 - Atualizar contrato");
        System.out.println("5 - Gerenciar contrato");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    public static void interfaceReports(){
        System.out.println("1 - Ativo");
        System.out.println("2 - Encerrado");
        System.out.println("3 - Cancelado");
        System.out.print("\nEscolha uma opção: ");
    }
}
