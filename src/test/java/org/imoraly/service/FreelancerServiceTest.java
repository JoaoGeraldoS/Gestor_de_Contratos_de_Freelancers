package org.imoraly.service;

import org.imoraly.model.Freelancer;
import org.imoraly.repository.IFreelancerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FreelancerServiceTest {

    @Mock
    private IFreelancerRepository repository;

    @InjectMocks
    private FreelancerService service;

    @Test
    @DisplayName("Testa a criação do freelancer se esta correta")
    void createFreelancerTest() {
        Freelancer freelancer = new Freelancer();

        freelancer.setName("Joao");
        freelancer.setEmail("joao@gmail.com");
        freelancer.setCpf("1223456677");
        freelancer.setSpecialty("Front end");

        service.createFreelancer(freelancer);

        verify(repository, times(1)).createFreelancer(freelancer);
    }

    @Test
    @DisplayName("Verifica se o retorno é null")
    void readOneFreelancer() {
        Assertions.assertThrows(RuntimeException.class, () -> service.readOneFreelancer(1));
    }

    @Test
    void updateFreelancer() {
    }

    @Test
    void deleteFreelancer() {
    }
}