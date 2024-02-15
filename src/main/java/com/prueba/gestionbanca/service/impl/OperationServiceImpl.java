package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.OperationService;
import com.prueba.gestionbanca.util.Constante;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.Utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * a.
 * <b>Class</b>: OperationServiceImpl <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 22, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Service
public class OperationServiceImpl implements OperationService {

  @Autowired
  private ClientRepository clientRepo;


  private final ReactiveMongoTemplate mongoTemplate;

  @Autowired
  public OperationServiceImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  /**
  * .
  *
  * @param depositRequest a.
  * @param operationType a.
  * @return addMovementToAccount
  */
  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public Mono<AccountOperationResponse> registerMovementAccount(MovementRequest depositRequest,
                                                                EnumOperationType operationType) {
    EnumOperationType operation;
    BigDecimal mountOperation;
    if (operationType.equals(EnumOperationType.DEPOSITO)) {
      operation = EnumOperationType.DEPOSITO;
      mountOperation = depositRequest.getAmount();
    } else {
      operation = EnumOperationType.RETIRO;
      mountOperation = depositRequest.getAmount().negate();
    }


    String numOperation = String.valueOf(Utils.generateNumberOperation());

    Movements mevement = Movements.builder()
                        .numberOperation(numOperation)
                        .dateOperation(Utils.convertDateToString(new Date()))
                        .operationType(operation)
                        .amount(mountOperation)
                        .build();

    long numberOperations = Objects.requireNonNull(
                            clientRepo.findAccountByNumber(depositRequest.getNumberAccount())
                                                    .block())
                                                    .getProduct().getAccount().stream()
                                                    .flatMap(account -> account.getMovements().stream())
                                                    .count();
    boolean existComision;
    BigDecimal amountNet = BigDecimal.ZERO;
    if (numberOperations >= Constante.MAX_TRANSACTION) {
        existComision = true;

        amountNet = mountOperation.subtract(Constante.COMISSION).setScale(2, RoundingMode.UP);

        mevement = Movements.builder()
                .numberOperation(numOperation)
                .dateOperation(Utils.convertDateToString(new Date()))
                .operationType(operation)
                .comission(Constante.COMISSION)
                .grossAmount(mountOperation)
                .amount(amountNet)
                .build();
    } else {
        existComision = false;
    }


    Query query = new Query(Criteria.where("product.account.number")
            .is(depositRequest.getNumberAccount()));
    Update update = new Update().push("product.account.$.movements", mevement);
    mongoTemplate.updateFirst(query, update, Client.class).subscribe();


    return clientRepo.findAccountByNumber(depositRequest.getNumberAccount())
              .flatMap(x -> {

                Optional<Account> account = x.getProduct().getAccount()
                        .stream()
                        .filter(a -> a.getMovements()
                                .stream()
                                .anyMatch(movement -> movement.getNumberOperation()
                                        .equals(numOperation)))
                        .findFirst();

                BigDecimal totalBalance = x.getProduct().getAccount()
                          .stream()
                          .flatMap(ac -> ac.getMovements().stream())
                          .filter(mov -> mov.getOperationType().equals(EnumOperationType.DEPOSITO)
                                      || mov.getOperationType().equals(
                                              EnumOperationType.DEPOSITO_TRANSF_PROPIA)
                                      || mov.getOperationType().equals(
                                              EnumOperationType.DEPOSITO_TRANSF_TERCERO))
                          .map(Movements::getAmount)
                          .reduce(BigDecimal.ZERO, BigDecimal::add);


                if (operation.equals(EnumOperationType.RETIRO)) {
                  totalBalance = totalBalance.subtract(depositRequest.getAmount());
                }

                updateBalanceAccount(depositRequest.getNumberAccount(), totalBalance);

                AccountOperationResponse accountOpeReponse = AccountOperationResponse.builder()
                        .numberOperation(numOperation)
                        .typeAccount(account.get().getType())
                        .card(account.get().getCard())
                        .number(account.get().getNumber())
                        .amount(depositRequest.getAmount())
                        .balanceTotal(totalBalance)
                        .build();
                if (existComision) {
                    accountOpeReponse = AccountOperationResponse.builder()
                            .numberOperation(numOperation)
                            .typeAccount(account.get().getType())
                            .card(account.get().getCard())
                            .number(account.get().getNumber())
                            .comision(Constante.COMISSION)
                            .grossAmount(depositRequest.getAmount())
                            .amount(mountOperation.subtract(Constante.COMISSION)
                                    .setScale(2, RoundingMode.UP))
                            .balanceTotal(totalBalance)
                            .build();
                }

                return Mono.just(accountOpeReponse);
              });


  }

  /**
   * a.
   *
   * @param numberAccount a.
   * @param totalBalance a.
   */
  @SuppressWarnings("checkstyle:Indentation")
  public void updateBalanceAccount(String numberAccount, BigDecimal totalBalance) {
      Query query = new Query(Criteria.where("product.account.number").is(numberAccount));
      Update update = new Update().set("product.account.$.balance", totalBalance);
      mongoTemplate.updateFirst(query, update, Client.class).subscribe();
  }

}


