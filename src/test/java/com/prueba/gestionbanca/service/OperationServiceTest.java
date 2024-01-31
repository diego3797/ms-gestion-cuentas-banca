package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.impl.OperationServiceImpl;
import com.prueba.gestionbanca.util.EnumOperationType;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OperationServiceTest {

    @Mock
    private ClientRepository clientRepo;

    @Mock
    private ReactiveMongoTemplate mongoTemplate;

    @InjectMocks
    private OperationServiceImpl operationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterMovementAccount() {

        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        MovementRequest depositRequest = MovementRequest.builder()
                .numberAccount("1992001451155")
                .mount(new BigDecimal(100.00))
                .build();
        EnumOperationType operation = EnumOperationType.DEPOSITO;
        Query query = new Query(Criteria.where("product.account.number")
                .is(depositRequest.getNumberAccount()));
        Update update = new Update().push("product.account.$.movements",
                Movements.builder()
                        .numberOperation("12345")
                        .dateOperation(new Date())
                        .operationType(operation)
                        .mount(depositRequest.getMount())
                        .build());


        when(clientRepo.findAccountByNumber(any())).thenReturn(Mono.just(Client.builder()
                .id(clientId)
                .build()));

        when(mongoTemplate.updateFirst(query, update, Client.class)).thenReturn(Mono.empty());




    }

    @Test
    public void testUpdateBalanceAccount() {

        String numberAccount = "1992001451155";
        BigDecimal totalBalance = BigDecimal.valueOf(1000);

        Query query = new Query(Criteria.where("product.account.number")
                .is(numberAccount));
        Update update = new Update().push("product.account.$.movements",
                Movements.builder()
                        .numberOperation("12345")
                        .dateOperation(new Date())
                        .operationType(EnumOperationType.DEPOSITO)
                        .mount(new BigDecimal(125.00))
                        .build());
        when(mongoTemplate.updateFirst(query, update, Client.class)).thenReturn(Mono.empty());

    }
}