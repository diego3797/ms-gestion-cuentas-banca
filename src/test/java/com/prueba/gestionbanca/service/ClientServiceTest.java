package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.*;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.impl.ClientServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepo;

    @InjectMocks
    private ClientServiceImpl clientService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddClientWithDataPersonal() {

        ClientRequest clientReq = ClientRequest.builder()
                .dataPersonal(DataPersonal.builder()
                                .name("TEST1")
                                .build())
                .build();
        Client expectedClient = Client.builder()
                .dataPersonal(DataPersonal.builder()
                        .name("TEST1")
                        .build())
                .build();
        when(clientRepo.save(any(Client.class))).thenReturn(Mono.just(expectedClient));


        Mono<Client> result = clientService.addClient(clientReq);


        StepVerifier.create(result)
                .expectNext(expectedClient)
                .verifyComplete();
        verify(clientRepo, times(1)).save(any(Client.class));
    }

    @Test
    void testAddClientWithDataCompany() {

        ClientRequest clientReq = ClientRequest.builder()
                .dataCompany(DataCompany.builder()
                        .name("COMPANY1")
                        .build())
                .build();
        Client expectedClient = Client.builder()
                .dataCompany(DataCompany.builder()
                        .name("COMPANY1")
                        .build())
                .build();
        when(clientRepo.save(any(Client.class))).thenReturn(Mono.just(expectedClient));


        Mono<Client> result = clientService.addClient(clientReq);


        StepVerifier.create(result)
                .expectNext(expectedClient)
                .verifyComplete();
        verify(clientRepo, times(1)).save(any(Client.class));
    }

    @Test
    void testFindClientById() {

        ObjectId clientIdBD = new ObjectId("5399aba6e4b0ae375bfdca88");
        String clientId = "5399aba6e4b0ae375bfdca88";
        Client expectedClient = Client.builder()
                .id(clientIdBD)
                .build();
        when(clientRepo.findById(clientId)).thenReturn(Mono.just(expectedClient));


        Mono<Client> result = clientService.findClientById(clientId);

        StepVerifier.create(result)
                .expectNext(expectedClient)
                .verifyComplete();
        verify(clientRepo, times(1)).findById(clientId);
    }

    @Test
    void testFindAll() {

        Client client1 = Client.builder()
                .dataPersonal(DataPersonal.builder()
                        .documentType("1")
                        .documentNumber("45870406")
                        .name("ALEXANDRA")
                        .lastFather("TORRES")
                        .lastMother("CURIOSO")
                        .build())
                .build();
        Client client2 = Client.builder()
                .dataPersonal(DataPersonal.builder()
                        .documentType("1")
                        .documentNumber("45870485")
                        .name("JORGE")
                        .lastFather("ALVARADO")
                        .lastMother("ROJAS")
                        .build())
                .build();
        when(clientRepo.findAll()).thenReturn(Flux.just(client1, client2));


        Flux<Client> result = clientService.findAll();


        StepVerifier.create(result)
                .expectNext(client1)
                .expectNext(client2)
                .verifyComplete();
        verify(clientRepo, times(1)).findAll();
    }


    @Test
    public void testUpdateClient() {

        ClientDto clientDto = new ClientDto();

        Client client = new Client();

        when(clientRepo.findById(clientDto.getId())).thenReturn(Mono.just(client));
        when(clientRepo.save(any())).thenReturn(Mono.just(client));

        Mono<Client> result = clientService.update(clientDto);

    }

    @Test
    void testDelete() {

        String clientId = "5399aba6e4b0ae375bfdca88";
        when(clientRepo.deleteById(clientId)).thenReturn(Mono.empty());


        Mono<Void> result = clientService.delete(clientId);


        StepVerifier.create(result)
                .verifyComplete();
        verify(clientRepo, times(1)).deleteById(clientId);
    }


}
