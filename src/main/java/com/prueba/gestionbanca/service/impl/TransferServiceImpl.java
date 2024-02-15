package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.TransferRequest;
import com.prueba.gestionbanca.expose.response.TransferOperationResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.TransferService;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.EnumTransferType;
import com.prueba.gestionbanca.util.Utils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * a.
 * <b>Class</b>: TransferServiceImpl <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 29, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Service
@Slf4j
public class TransferServiceImpl implements TransferService {

  @Autowired
  private ClientRepository clientRepo;

  private final ReactiveMongoTemplate mongoTemplate;

  @Autowired
  public TransferServiceImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Mono<TransferOperationResponse> registerTransferAccount(TransferRequest transferRequest,
                                                                 EnumTransferType transferType) {

    String numOperation = String.valueOf(Utils.generateNumberOperation());

    Account accountFrom = registerTransferInAccountFrom(transferRequest,
            numOperation, transferType).block();
    Account accountDestiny = registerTransferInAccountDestiny(transferRequest,
            numOperation, transferType).block();

    updateBalanceAccount(transferRequest.getNumberAccountFrom(),
            accountFrom.getBalance().subtract(transferRequest.getAmount()));
    updateBalanceAccount(transferRequest.getNumberAccountDestiny(),
              accountDestiny.getBalance().add(transferRequest.getAmount()));

    if (transferType.equals(EnumTransferType.CUENTA_PROPIA)) {

      return Mono.just(TransferOperationResponse.builder()
              .numberOperation(numOperation)
              .typeAccountFrom(accountFrom.getType())
              .numberAccountFrom(accountFrom.getNumber())
              .typeAccountDestiny(accountDestiny.getType())
              .numberAccountDestiny(accountDestiny.getNumber())
              .amountTransfer(transferRequest.getAmount())
              .build());
    } else {

      String document = getDataPersonal(transferRequest.getNumberAccountDestiny())
              .get("numberDocument");
      String fullName = getDataPersonal(transferRequest.getNumberAccountDestiny())
              .get("fullName");

      return Mono.just(TransferOperationResponse.builder()
                .numberOperation(numOperation)
                .typeAccountFrom(accountFrom.getType())
                .numberAccountFrom(accountFrom.getNumber())
                .typeAccountDestiny(accountDestiny.getType())
                .numberAccountDestiny(accountDestiny.getNumber())
                .numberDocumentThird(document)
                .nameThird(fullName)
                .amountTransfer(transferRequest.getAmount())
                .build());
    }


  }

  /**
   * a.
   */
  public void updateBalanceAccount(String numberAccount, BigDecimal totalBalance) {
    Query query = createQuery(numberAccount);
    Update update = new Update().set("product.account.$.balance", totalBalance);
    mongoTemplate.updateFirst(query, update, Client.class).subscribe();
  }



  /**
   * a.

   * @return Account
   */
  public Mono<Account> registerTransferInAccountFrom(TransferRequest transferRequest,
                                                     String numOperation,
                                                     EnumTransferType transferType) {

    EnumOperationType operation;
    if (transferType.equals(EnumTransferType.CUENTA_PROPIA)) {
      operation = EnumOperationType.TRANSF_CUENTA_PROPIA;
    } else {
      operation = EnumOperationType.TRANSF_CUENTA_TERCERO;
    }



    Query query = createQuery(transferRequest.getNumberAccountFrom());
    Update update = new Update().push("product.account.$.movements",
            Movements.builder()
                    .numberOperation(numOperation)
                    .dateOperation(Utils.convertDateToString(new Date()))
                    .operationType(operation)
                    .numberAccountDestiny(transferRequest.getNumberAccountDestiny())
                    .amount(transferRequest.getAmount().negate())
                    .build());

    mongoTemplate.updateFirst(query, update, Client.class).subscribe();

    return clientRepo.findAccountByNumber(transferRequest.getNumberAccountFrom())
              .flatMap(x -> {
                Optional<Account> accountOptional = x.getProduct().getAccount()
                          .stream()
                          .filter(account -> account.getMovements()
                                  .stream()
                                  .anyMatch(movement -> movement.getNumberOperation()
                                          .equals(numOperation)))
                          .findFirst();
                return Mono.justOrEmpty(accountOptional);
              });

  }

  /**
   * a.

   * @return Account
   */
  public Mono<Account> registerTransferInAccountDestiny(TransferRequest transferRequest,
                                                        String numOperation,
                                                        EnumTransferType transferType) {

    EnumOperationType operation;
    if (transferType.equals(EnumTransferType.CUENTA_PROPIA)) {
      operation = EnumOperationType.TRANSF_CUENTA_PROPIA;
    } else {
      operation = EnumOperationType.TRANSF_CUENTA_TERCERO;
    }

    Query query = createQuery(transferRequest.getNumberAccountDestiny());
    Update update = new Update().push("product.account.$.movements",
            Movements.builder()
                    .numberOperation(numOperation)
                    .dateOperation(Utils.convertDateToString(new Date()))
                    .operationType(operation)
                    .numberAccountFrom(transferRequest.getNumberAccountFrom())
                    .amount(transferRequest.getAmount())
                    .build());

    mongoTemplate.updateFirst(query, update, Client.class).subscribe();

    return clientRepo.findAccountByNumber(transferRequest.getNumberAccountDestiny())
            .flatMap(x -> {
              Optional<Account> accountOptional = x.getProduct().getAccount()
                        .stream()
                        .filter(account -> account.getMovements()
                                .stream()
                                .anyMatch(movement -> movement.getNumberOperation()
                                                .equals(numOperation)))
                        .findFirst();
              return Mono.justOrEmpty(accountOptional);
            });
  }



  /**
   * a.

   * @return String, String
   */
  private Map<String, String> getDataPersonal(String numberAccount) {

    Client client = clientRepo.findClientByNumberAccount(numberAccount).block();
    String document = client.getDataPersonal().getDocumentNumber();
    String fullName = client.getDataPersonal().getName() + " "
                    + client.getDataPersonal().getLastFather() + " "
                    + client.getDataPersonal().getLastMother();

    Map<String, String> resultData = new HashMap<>();
    resultData.put("numberDocument", document);
    resultData.put("fullName", fullName);

    return resultData;

  }

  private Query createQuery(String number) {
    return new Query(Criteria.where("product.account.number").is(number));
  }

}




