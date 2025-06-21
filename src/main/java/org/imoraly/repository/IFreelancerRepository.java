package org.imoraly.repository;

import org.imoraly.model.Freelancer;

import java.util.List;

public interface IFreelancerRepository {
    void createFreelancer(Freelancer freelancer);
    List<Freelancer> readFreelances();
    Freelancer readOnFreelancer(int id);
    void updateFreelancer(Freelancer freelancer, int id);
    void deleteFreelancer(int id);
    Freelancer searchFreelancer(String name);
}
