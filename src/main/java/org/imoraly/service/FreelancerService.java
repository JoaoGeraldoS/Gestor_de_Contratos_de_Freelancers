package org.imoraly.service;

import org.imoraly.model.Freelancer;
import org.imoraly.repository.IFreelancerRepository;

public class FreelancerService {

    private final IFreelancerRepository repository;

    public FreelancerService(IFreelancerRepository repository) {
        this.repository = repository;
    }

    public void createFreelancer(Freelancer freelancer) {
        if(freelancer == null) {
            throw new RuntimeException("Erro ao salvar freelancer");
        }

        repository.createFreelancer(freelancer);
    }

    public void readAllFreelancer() {
        repository.readFreelances().stream()
                .filter(Freelancer::isActive)
                .forEach(System.out::println);
    }

    public void readOneFreelancer(int id) {
        Freelancer freelancer = repository.readOnFreelancer(id);

        if(freelancer == null) {
            throw new RuntimeException("Freelancer não existe");
        }

        System.out.println(freelancer);
    }

    public void updateFreelancer(Freelancer freelancer, int id) {
        Freelancer buscar = repository.readOnFreelancer(id);

        if(buscar == null) {
            throw new RuntimeException("Freelancer não existe");
        }

        repository.updateFreelancer(freelancer, id);
    }

    public void deleteFreelancer(int id) {
        Freelancer freelancer = repository.readOnFreelancer(id);

        if(freelancer == null) {
            throw new RuntimeException("Freelancer não existe");
        }

        repository.deleteFreelancer(id);
    }
}
