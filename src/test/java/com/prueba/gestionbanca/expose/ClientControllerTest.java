package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.service.ClientService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void testAddClient() {

        ClientRequest clientRequest = new ClientRequest();
        when(clientService.addClient(clientRequest)).thenReturn(Mono.just(new Client()));

        ResponseEntity<Mono<Client>> response = clientController.addClient(clientRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(clientService, times(1)).addClient(clientRequest);
    }

    /*
    @Test
    void testDeleteClient() {
        // Arrange
        String id = "5399aba6e4b0ae375bfdca88";

        // Act
        clientController.deleteClient(id);

        // Assert
        verify(clientService, times(1)).delete(id);
    }*/

    @Test
    void testGetClientById() {

        String clientId = "5399aba6e4b0ae375bfdca88";
        when(clientService.findClientById(clientId)).thenReturn(Mono.just(new Client()));

        ResponseEntity<Mono<Client>> response = clientController.getClientById(clientId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(clientService, times(1)).findClientById(clientId);
    }

    @Test
    void testGetClients() {

        when(clientService.findAll()).thenReturn(Flux.just(new Client()));


        Flux<Client> response = clientController.getClients();


        assertThat(response).isNotNull();
        verify(clientService, times(1)).findAll();
    }

    @Test
    void testUpdClient() {

        ClientDto clientRequest = new ClientDto();
        when(clientService.update(clientRequest)).thenReturn(Mono.just(new Client()));


        ResponseEntity<Mono<Client>> response = clientController.updClient(clientRequest);


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(clientService, times(1)).update(clientRequest);
    }
}
