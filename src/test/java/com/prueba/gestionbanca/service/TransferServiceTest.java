package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.TransferRequest;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.impl.TransferServiceImpl;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.EnumTransferType;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

public class TransferServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ReactiveMongoTemplate mongoTemplate;

    @InjectMocks
    private TransferServiceImpl transferService;

    @Test
    public void testRegisterTransferInAccountFrom() {

        TransferRequest transferRequest = TransferRequest.builder()
                .numberAccountFrom("8965231458796")
                .numberAccountDestiny("199200145122")
                .mount(new BigDecimal(200.00))
                .build();
        String numOperation = "99991";
        ObjectId clientId = new ObjectId("65b3434773f58f7b6e6dee69");
        EnumTransferType transferType = EnumTransferType.CUENTA_PROPIA;


        Query query = new Query(Criteria.where("product.account.number")
                .is(transferRequest.getNumberAccountFrom()));
        Update update = new Update().push("product.account.$.movements",
                Movements.builder()
                        .numberOperation(numOperation)
                        .dateOperation(new Date())
                        .operationType(EnumOperationType.DEPOSITO_TRANSF_PROPIA)
                        .numberAccountDestiny(transferRequest.getNumberAccountDestiny())
                        .mount(transferRequest.getMount())
                        .build());



    }

}
