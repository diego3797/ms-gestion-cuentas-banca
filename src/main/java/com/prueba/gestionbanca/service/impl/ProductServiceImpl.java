package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceCreditResponse;
import com.prueba.gestionbanca.expose.response.BalanceMovementsResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Credit;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * a.
 * <b>Class</b>: ProductServiceImpl <br/>
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
public class ProductServiceImpl implements ProductService {

  /**
   * a.
   */
  @Autowired
  private ClientRepository clientRepo;

  /**
   * a.
   *
   * @param number a.
   * @return findProducByNumber
   */
  @Override
  public Mono<BalanceAccountResponse> findProducByNumber(String number) {
    if (number.length() == 16) {
      return findProductByCardNumber(number);
    }

    if (number.length() == 13 || number.length() == 14) {
      return findProductByNumberAccount(number);
    }

    return null;
  }

  /**
   * a.
   *
   * @param document a.
   * @return findProductByDocumentNumber
   */
  @Override
  public Mono<ProductBalanceResponse> findProductByDocumentNumber(String document) {
    return clientRepo.findProductsByDocumentNumber(document)
            .flatMap(x -> {
              List<BalanceAccountResponse> balanceAccountLst = x.getProduct().getAccount().stream()
                      .map(account -> new BalanceAccountResponse(account.getType(),
                                                                 account.getCard(),
                                                                 account.getNumber(),
                                                                 account.getBalance()))
                      .collect(Collectors.toList());

              List<BalanceCreditResponse> balanceCreditLst = x.getProduct().getCredit().stream()
                      .map(credit -> new BalanceCreditResponse(credit.getType(),
                                                               credit.getCard(),
                                                               credit.getNumber(),
                                                               credit.getLimitCredit(),
                                                               credit.getBalance()))
                      .collect(Collectors.toList());

              BigDecimal accountTotal = x.getProduct().getAccount().stream()
                      .map(Account::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              BigDecimal creditTotal = x.getProduct().getCredit().stream()
                      .map(Credit::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              return Mono.just(ProductBalanceResponse.builder()
                              .account(balanceAccountLst)
                              .credit(balanceCreditLst)
                              .totalBalance(accountTotal.add(creditTotal))
                      .build());
            });
  }

  /**
   * a.
   *
   * @param number a.
   * @return findMovementByNumberAccount
   */
  @Override
  public Mono<BalanceMovementsResponse> findMovementByNumberAccount(String number) {
    return clientRepo.findAccountByNumber(number).flatMap(x -> {
      Optional<Account> account = x.getProduct().getAccount()
              .stream()
              .findFirst();

      return Mono.just(BalanceMovementsResponse.builder()
              .type(account.get().getType())
              .card(account.get().getCard())
              .number(account.get().getNumber())
              .movements(account.get().getMovements())
              .balance(account.get().getBalance())
              .build());
    });
  }

  /**
  * a.
  *
  * @param number a.
  * @return findProductByCardNumber
  */
  public Mono<BalanceAccountResponse> findProductByCardNumber(final String number) {
    return clientRepo.findCreditByNumber(number).flatMap(x -> {
      Optional<Credit> credit = x.getProduct().getCredit()
                 .stream()
                 .findFirst();

      return Mono.just(BalanceAccountResponse.builder()
                         .type(credit.get().getType())
                         .card(credit.get().getCard())
                         .number(credit.get().getNumber())
                         .balance(credit.get().getBalance())
                 .build());
    });

  }


  /**
   * a.
   *
   *
   *
   * @param number a.
   * @return findProductByNumberAccount
   */
  public Mono<BalanceAccountResponse> findProductByNumberAccount(final String number) {
    return clientRepo.findAccountByNumber(number).flatMap(x -> {
      Optional<Account> account = x.getProduct().getAccount()
              .stream()
              .findFirst();

      return Mono.just(BalanceAccountResponse.builder()
              .type(account.get().getType())
              .card(account.get().getCard())
              .number(account.get().getNumber())
              .balance(account.get().getBalance())
              .build());
    });
  }
}
