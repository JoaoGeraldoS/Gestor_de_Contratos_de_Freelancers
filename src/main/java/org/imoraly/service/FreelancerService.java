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

    public Freelancer readOneFreelancer(int id) {
        Freelancer freelancer = repository.readOnFreelancer(id);

        if(freelancer == null) {
            throw new RuntimeException("Freelancer não existe");
        }

        return freelancer;
    }

    public void updateFreelancer(Freelancer freelancer, int id) {
        repository.updateFreelancer(freelancer, id);
    }

    public void deleteFreelancer(int id) {
        repository.deleteFreelancer(id);
    }

    public int searchFreelancer(String name) {
        var freelancer = repository.searchFreelancer(name);

        if(freelancer == null) {
            throw new RuntimeException("freelancer nao existe");
        }
        return freelancer.getId();
    }

}
