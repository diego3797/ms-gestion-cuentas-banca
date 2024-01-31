package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.service.ClientService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void testAddClient() {

        ClientRequest clientRequest = ClientRequest.builder()
                .clientType("VIP")
                .build();
        Client client = Client.builder()
                .clientType("VIP")
                .build();


        when(clientService.addClient(clientRequest)).thenReturn(Mono.just(client));

        Mono<ResponseEntity<Client>> result = clientController.addClient(clientRequest);

        ResponseEntity<Client> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(client);
        assertEquals(expectedResponse, result.block());
    }

    @Test
    void testDeleteClient() {

        String clientId = "5399aba6e4b0ae375bfdca88";

        when(clientService.delete(clientId)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> result = clientController.deleteClient(clientId);

        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        assertEquals(expectedResponse, result.block());
    }



    @Test
    void testGetClientById() {



        String clientId = "5399aba6e4b0ae375bfdca88";
        ObjectId cliId = new ObjectId("5399aba6e4b0ae375bfdca88");
        Client client = Client.builder()
                .id(cliId)
                .build();

        when(clientService.findClientById(clientId)).thenReturn(Mono.just(client));

        Mono<ResponseEntity<Client>> result = clientController.getClientById(clientId);

        ResponseEntity<Client> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(client);
        assertEquals(expectedResponse, result.block());

    }

    @Test
    void testGetClients() {

        ObjectId cli1Id = new ObjectId("5399aba6e4b0ae375bfdca88");
        ObjectId cli2Id = new ObjectId("65b3434773f58f7b6e6dee69");
        Client client1 =  Client.builder()
                                .id(cli1Id)
                                .build();
        Client client2 =  Client.builder()
                                .id(cli2Id)
                                .build();
        Flux<Client> clients = Flux.just(client1, client2);

        when(clientService.findAll()).thenReturn(clients);

        Flux<Client> result = clientController.getClients();

        assertEquals(2, result.count().block());
    }

    @Test
    void testUpdClient() {

        ClientDto clientDto = ClientDto.builder()
                .phono("964552226")
                .build();
        Client client = Client.builder()
                .phono("964552226")
                .build();

        when(clientService.update(clientDto)).thenReturn(Mono.just(client));

        Mono<ResponseEntity<Client>> result = clientController.updClient(clientDto);

        ResponseEntity<Client> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(client);
        assertEquals(expectedResponse, result.block());
    }
}
