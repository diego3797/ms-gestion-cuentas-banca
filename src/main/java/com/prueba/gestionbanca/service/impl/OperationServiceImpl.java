package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.OperationService;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.Utils;
import java.math.BigDecimal;
import java.util.Date;
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
  * @param operation a.
  * @return addMovementToAccount
  */
  @Override
  public Mono<AccountOperationResponse> registerMovementAccount(MovementRequest depositRequest,
                                                                EnumOperationType operation) {
    String numOperation = String.valueOf(Utils.generateNumber());

    Query query = new Query(Criteria.where("product.account.number")
            .is(depositRequest.getNumberAccount()));
    Update update = new Update().push("product.account.$.movements",
            Movements.builder()
                .numberOperation(numOperation)
                .dateOperation(new Date())
                .operationType(operation)
                .mount(depositRequest.getMount())
                .build());

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
                          .filter(mov -> mov.getOperationType().equals(EnumOperationType.DEPOSITO))
                          .map(Movements::getMount)
                          .reduce(BigDecimal.ZERO, BigDecimal::add);


                if (operation.equals(EnumOperationType.RETIRO)) {
                  totalBalance = totalBalance.subtract(depositRequest.getMount());
                }

                updateBalanceAccount(depositRequest.getNumberAccount(), totalBalance);

                return Mono.just(AccountOperationResponse.builder()
                        .numberOperation(numOperation)
                        .typeAccount(account.get().getType())
                        .card(account.get().getCard())
                        .number(account.get().getNumber())
                        .balance(totalBalance)
                        .build());
              });


  }

  @SuppressWarnings("checkstyle:Indentation")
  private void updateBalanceAccount(String numberAccount, BigDecimal totalBalance) {
      Query query = new Query(Criteria.where("product.account.number").is(numberAccount));
      Update update = new Update().set("product.account.$[].balance", totalBalance);
      mongoTemplate.updateFirst(query, update, Client.class).subscribe();
  }

}


