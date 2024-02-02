package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.impl.CreditServiceImpl;
import com.prueba.gestionbanca.util.Constante;
import com.prueba.gestionbanca.util.EnumOperationType;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

public class CreditServiceTest {

    @Mock
    private ClientRepository clientRepo;

    @Mock
    private ReactiveMongoTemplate mongoTemplate;

    @InjectMocks
    private CreditServiceImpl creditService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterMovementCredit_PagoCredit_Valid() {


        String operacionCredit = Constante.PAGO_CREDIT;
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        Query query = new Query(Criteria.where("product.credit.card")
                .is("3777548264544"));
        Update update = new Update().push("product.credit.$.movements",
                Movements.builder()
                        .numberOperation("12345")
                        .dateOperation(new Date())
                        .operationType(EnumOperationType.PAGO_MINIMO)
                        .amount(new BigDecimal(150.00))
                        .build());

        when(clientRepo.findCreditByNumber("3777548264544")).thenReturn(Mono.just(Client.builder()
                        .id(clientId)
                        .build()));

        when(mongoTemplate.updateFirst(query, update, Client.class)).thenReturn(Mono.empty());

    }


}