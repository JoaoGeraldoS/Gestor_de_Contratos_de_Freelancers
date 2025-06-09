package org.imoraly;

import org.imoraly.dao.ClientDAO;
import org.imoraly.dao.ContractDAO;
import org.imoraly.dao.FreelancerDAO;
import org.imoraly.model.Client;
import org.imoraly.model.Contract;
import org.imoraly.model.Freelancer;

public class Main {

    public static void main(String[] args) {
//        FreelancerDAO freelancerDAO = new FreelancerDAO();

        Freelancer freelancer = new Freelancer();

        freelancer.setName("Maria");
        freelancer.setEmail("maria@gmail.com");
        freelancer.setCpf("12223456789");
        freelancer.setSpecialty("Front end");

//        freelancerDAO.createFreelancer(freelancer);

//        ClientDAO clientDAO = new ClientDAO();
        Client client = new Client();

        client.setName("Abraão");
        client.setCnpjOrCpf("1233345356");
        client.setEmail("abraao@gmail.com");
        client.setTelephone("123456378");

//        clientDAO.createClient(client);

//        ContractDAO contractDAO = new ContractDAO();
        Contract contract = new Contract();

        contract.setDescription("Criar um site simples");
        contract.setHourlyRate(10.00);
        contract.setContractedHours(4);
        contract.setTax(5.50);
        contract.setBonus(30.00);
        contract.setStatus("Pendente");
        contract.setFreelancerId(4);
        contract.setClientId(2);

//        contractDAO.createContract(contract);

//        contractDAO.createContract(contract);

//        contractDAO.readContract().forEach(System.out::println);

    }
}