package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.CreditRequest;
import com.prueba.gestionbanca.expose.response.CreditOperationResponse;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Credit;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.CreditService;
import com.prueba.gestionbanca.util.Constante;
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
 * <b>Class</b>: CreditServiceImpl <br/>
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
public class CreditServiceImpl implements CreditService {

  @Autowired
  private ClientRepository clientRepo;


  private final ReactiveMongoTemplate mongoTemplate;

  @Autowired
  public CreditServiceImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Mono<CreditOperationResponse> registerMovementCredit(CreditRequest creditRequest,
                                                              String operacionCredit) {

    String numOperation = String.valueOf(Utils.generateNumberOperation());

    Query query = new Query(Criteria.where("product.credit.card").is(creditRequest.getCard()));

    EnumOperationType enumOpe;
    BigDecimal amount;
    if (operacionCredit.equals(Constante.PAGO_CREDIT)) {
      enumOpe = EnumOperationType.getByCode(Integer.parseInt(creditRequest.getPayType()));
      amount = creditRequest.getAmount();
    } else {
      enumOpe = EnumOperationType.CONSUMO;
      amount = creditRequest.getAmountConsume();
    }

    Update update = new Update().push("product.credit.$.movements", Movements.builder()
                                                                  .numberOperation(numOperation)
                                                                  .dateOperation(new Date())
                                                                  .operationType(enumOpe)
                                                                  .amount(amount)
                                                                  .build());

    if (!validMountOperation(creditRequest.getCard(), operacionCredit, amount)) {
      return Mono.just(CreditOperationResponse.builder()
                .message("El monto de operacion superó el limite de credito")
                .build());
    }

    mongoTemplate.updateFirst(query, update, Client.class).subscribe();


    return clientRepo.findCreditByNumber(creditRequest.getCard())
               .flatMap(x -> {

                 Optional<Credit> credit = x.getProduct().getCredit()
                         .stream()
                         .filter(a -> a.getMovements()
                                 .stream()
                                 .anyMatch(movement -> movement.getNumberOperation()
                                                                .equals(numOperation)))
                         .findFirst();

                 BigDecimal totalBalancePay = x.getProduct().getCredit()
                         .stream()
                         .flatMap(ac -> ac.getMovements().stream())
                         .filter(mov -> mov.getOperationType().equals(EnumOperationType.PAGO_MINIMO)
                                     || mov.getOperationType().equals(EnumOperationType.PAGO_TOTAL))
                         .map(Movements::getAmount)
                         .reduce(BigDecimal.ZERO, BigDecimal::add);

                 BigDecimal totalBalanceConsume = x.getProduct().getCredit()
                           .stream()
                           .flatMap(ac -> ac.getMovements().stream())
                           .filter(mov -> mov.getOperationType().equals(EnumOperationType.CONSUMO))
                           .map(Movements::getAmount)
                           .reduce(BigDecimal.ZERO, BigDecimal::add);

                 BigDecimal limit = x.getProduct().getCredit()
                           .stream()
                           .map(Credit::getLimitCredit)
                           .findFirst().get();

                 BigDecimal totalBalance = limit.add(totalBalanceConsume.negate());
                 totalBalance = totalBalance.add(totalBalancePay);
                 updateBalanceCredit(creditRequest.getCard(), totalBalance);

                 return Mono.just(CreditOperationResponse.builder()
                         .numberOperation(numOperation)
                         .typeCredit(credit.get().getType())
                         .card(credit.get().getCard())
                         .limitCredit(credit.get().getLimitCredit())
                         .balance(totalBalance)
                         .build());
               });
  }

  private void updateBalanceCredit(String numberCard, BigDecimal totalBalance) {
    Query query = new Query(Criteria.where("product.credit.card").is(numberCard));
    Update update = new Update().set("product.credit.$[].balance", totalBalance);
    mongoTemplate.updateFirst(query, update, Client.class).subscribe();
  }

  private Mono<Client> getCreditbyNumber(String card) {
    return clientRepo.findCreditByNumber(card);
  }

  private boolean validMountOperation(String card, String operation, BigDecimal mountNew) {

    BigDecimal balance = getCreditbyNumber(card).block()
            .getProduct()
            .getCredit()
            .stream()
            .map(Credit::getBalance)
            .findFirst()
            .orElse(BigDecimal.ZERO);

    BigDecimal limitCredit = getCreditbyNumber(card).block()
             .getProduct()
             .getCredit()
             .stream()
             .map(Credit::getLimitCredit)
             .findFirst()
             .orElse(BigDecimal.ZERO);

    boolean response;
    if (operation.equals(Constante.PAGO_CREDIT)) {
      BigDecimal mountCompare = balance.add(mountNew);
      response = mountCompare.compareTo(limitCredit) < 0;
    } else {
      response = mountNew.compareTo(balance) < 0;
    }

    return response;

  }
}
