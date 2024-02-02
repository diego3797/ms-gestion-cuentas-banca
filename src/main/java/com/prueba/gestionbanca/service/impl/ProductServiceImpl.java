package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.AccountRequest;
import com.prueba.gestionbanca.expose.response.*;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Credit;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ProductService;
import com.prueba.gestionbanca.util.Constante;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.Utils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

  private final ReactiveMongoTemplate mongoTemplate;

  @Autowired
  public ProductServiceImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  /**
   * a.
   *
   * @param accountRequest a.
   * @return ProductoAccountResponse
   */
  @Override
  public Mono<ProductoAccountResponse> registerProduct(AccountRequest accountRequest) {

    Mono<Client> cli = clientRepo.findProductsByDocumentNumber(accountRequest.getNumberDocument());
    Client cliObj = cli.block();

    if (Objects.requireNonNull(cliObj).getClientType().equals("Empresarial")
            && Objects.requireNonNull(cliObj).getProfileType().equals("PYME")) {
      if (!validationProfileCompany(Objects.requireNonNull(cliObj))) {
        return Mono.just(ProductoAccountResponse.builder()
                .msg("La empresa no cuenta con Crédito o Cuenta Corriente")
                .build());
      }
    }

    if (Objects.requireNonNull(cliObj).getClientType().equals("Personal")
            && Objects.requireNonNull(cliObj).getProfileType().equals("VIP")) {
      if (!validationProfilePersonalProducts(Objects.requireNonNull(cliObj))) {
        return Mono.just(ProductoAccountResponse.builder()
                              .msg("El cliente no cuenta con Crédito")
                              .build());
      }

      if (!validationProfilePersonalMount(accountRequest.getAmount())) {
        return Mono.just(ProductoAccountResponse.builder()
                            .msg("El monto minimo de apertura de Cuenta es de 500")
                            .build());
      }
    }



    String cadQuery = "dataPersonal.documentNumber";
    if (accountRequest.getClientType().equals("Empresarial")) {
      cadQuery = "dataCompany.ruc";
    }

    String numCuenta = String.valueOf(Utils.generateNumberAccount());
    if (accountRequest.getAccountType().equals("Cta Corriente")) {
      numCuenta = String.valueOf(Utils.generateNumberCurrentAccount());
    }

    Query query = new Query(Criteria.where(cadQuery).is(accountRequest.getNumberDocument()));
    Update update = new Update().push("product.account", new Document()
                                      .append("type", accountRequest.getAccountType())
                                      .append("number", numCuenta)
                                      .append("movements", Arrays.asList(new Document()
                                              .append("numberOperation", String.valueOf(Utils.generateNumberOperation()))
                                              .append("dateOperation", new Date())
                                              .append("operationType", EnumOperationType.DEPOSITO)
                                              .append("amount", accountRequest.getAmount())
                                      ))
                                      .append("dateCreation", new Date())
                                      .append("sucursal", new Document()
                                              .append("id", String.valueOf(Utils.generateIdSucursal()))
                                              .append("name", accountRequest.getSucursal().getName())
                                              .append("address", accountRequest.getSucursal().getAddress())
                                              .append("nameOperator", accountRequest.getSucursal().getNameOperator())
                                              .append("nameManager", accountRequest.getSucursal().getNameManager())
                                      )
                                      .append("balance", accountRequest.getAmount())
    );

    mongoTemplate.updateFirst(query, update, Client.class).subscribe();

    return clientRepo.findAccountByNumberWithDataClient(numCuenta)
            .flatMap(x -> {

              ProductoAccountResponse response = ProductoAccountResponse.builder()
                                                  .clientType(x.getClientType())
                                                  .profileType(x.getProfileType())
                                                  .dataPersonal(x.getDataPersonal())
                                                  .product(x.getProduct())
                                                  .build();
              if (accountRequest.getAccountType().equals("Empresarial")) {
                response = ProductoAccountResponse.builder()
                        .clientType(x.getClientType())
                        .profileType(x.getProfileType())
                        .dataCompany(x.getDataCompany())
                        .product(x.getProduct())
                        .build();
              }

              return Mono.just(response);
            });
  }

  /**
   * a.
   *
   * @param number a.
   * @return findProducByNumber
   */
  @Override
  public Mono<BalanceAccountResponse> findProducByNumber(String number) {
    if (number.length() == Constante.DIGITS_CARD) {
      return findProductByCardNumber(number);
    }

    if (number.length() == Constante.DIGITS_ACCOUNT_CTACORRIENTE
            || number.length() == Constante.DIGITS_ACCOUNT_AHORRO) {
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

  /**
   * a.
   *
   *
   *
   * @param cli a.
   * @return validationProfilePersonalProducts
   */
  public boolean validationProfilePersonalProducts(Client cli) {
    return !cli.getProduct().getCredit().isEmpty();
  }

  /**
   * a.
   *
   *
   *
   * @param mount a.
   * @return validationProfilePersonalMount
   */
  public boolean validationProfilePersonalMount(BigDecimal mount) {
    return mount.compareTo(new BigDecimal(500)) >= 0;
  }

  /**
   * a.
   *
   *
   *
   * @param cli a.
   * @return validationProfileCompany
   */
  public boolean validationProfileCompany(Client cli) {

    boolean existCurrentAccount = cli.getProduct().getAccount()
                                            .stream()
                                            .anyMatch(s -> s.getType().equals("Cta Corriente"));

    boolean existCredit = cli.getProduct().getCredit().stream().findFirst().isPresent();


    return existCurrentAccount && existCredit;
  }
}
