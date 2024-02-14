package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceMovementsResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.impl.ProductServiceImpl;
import com.prueba.gestionbanca.util.Constante;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ClientRepository clientRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindProducByNumber_CardNumber() {


        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        String cardNumber = "1234567890123456";
        String producto = Constante.TARJETA_CREDITO.toString();
        when(clientRepo.findCreditByCardNumber(cardNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<BalanceAccountResponse> result = productService.findProducByNumber(cardNumber, producto);

    }

    @Test
    public void testFindProducByNumber_AccountNumber() {

        String accountNumber = "1992001451155";
        String producto = Constante.CUENTA.toString();
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        when(clientRepo.findAccountByNumber(accountNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<BalanceAccountResponse> result = productService.findProducByNumber(accountNumber, producto);

    }

    @Test
    public void testFindProductByDocumentNumber() {
        // Arrange
        String documentNumber = "45859966";
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        when(clientRepo.findProductsByDocumentNumber(documentNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<ProductBalanceResponse> result = productService.findProductByDocumentNumber(documentNumber);


    }

    @Test
    public void testFindMovementByNumberAccount() {

        String accountNumber = "1992001451155";
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        when(clientRepo.findAccountByNumber(accountNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<BalanceMovementsResponse> result = productService.findMovementByNumberAccount(accountNumber);

    }

    @Test
    public void testFindProductByCardNumber() {

        String cardNumber = "3777548264544";
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        when(clientRepo.findCreditByCardNumber(cardNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<BalanceAccountResponse> result = productService.findProductByCreditCardNumber(cardNumber);

    }

    @Test
    public void testFindProductByNumberAccount() {

        String accountNumber = "1992001451155";
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        when(clientRepo.findAccountByNumber(accountNumber)).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));


        //Mono<BalanceAccountResponse> result = productService.findProductByNumberAccount(accountNumber);

    }
}
