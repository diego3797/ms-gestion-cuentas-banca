package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.request.AccountRequest;
import com.prueba.gestionbanca.expose.request.AssociateRequest;
import com.prueba.gestionbanca.expose.response.AssociateResponse;
import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceCreditResponse;
import com.prueba.gestionbanca.expose.response.BalanceDebitResponse;
import com.prueba.gestionbanca.expose.response.BalanceMovementsResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import com.prueba.gestionbanca.expose.response.ProductoAccountResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Credit;
import com.prueba.gestionbanca.model.Debit;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ProductService;
import com.prueba.gestionbanca.util.Constante;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.Utils;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
                                              .append("numberOperation",
                                                String.valueOf(Utils.generateNumberOperation()))
                                              .append("dateOperation", new Date())
                                              .append("operationType", EnumOperationType.DEPOSITO)
                                              .append("amount", accountRequest.getAmount())
                                      ))
                                      .append("dateCreation", new Date())
                                      .append("sucursal", new Document()
                                              .append("id",
                                                 String.valueOf(Utils.generateIdSucursal()))
                                              .append("name",
                                                 accountRequest.getSucursal().getName())
                                              .append("address",
                                                 accountRequest.getSucursal().getAddress())
                                              .append("nameOperator",
                                                 accountRequest.getSucursal().getNameOperator())
                                              .append("nameManager",
                                                 accountRequest.getSucursal().getNameManager())
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
   * @param associateRequest a.
   * @return AssociateResponse
   */
  @Override
  public Mono<AssociateResponse> associateDebitCard(AssociateRequest associateRequest) {

    if (clientRepo.findAccountByNumberAndDocumentNumber(associateRequest.getDocumentNumber(),
            associateRequest.getAccountAssociate().getAccountNumber()).block() == null) {
      return Mono.just(AssociateResponse.builder()
                      .msg("El número de cuenta a asociar no existe en tus productos")
              .build());
    }

    Query query = new Query(
            new Criteria().orOperator(
                    Criteria.where("dataPersonal.documentNumber")
                            .is(associateRequest.getDocumentNumber()),
                    Criteria.where("dataCompany.ruc")
                            .is(associateRequest.getDocumentNumber())
            ).and("product.debit.numberCard")
                    .is(associateRequest.getDebitCard())
    );
    Update update = new Update().push("product.debit.$[].associatedAccount", new Document()
            .append("accountNumber", associateRequest.getAccountAssociate().getAccountNumber())
            .append("dateAssociate", new Date())

    );

    mongoTemplate.updateMulti(query, update, Client.class).subscribe();

    return clientRepo.findProductsByDocumentNumber(associateRequest.getDocumentNumber())
            .flatMap(cli -> {
              return Mono.just(AssociateResponse.builder()
                              .clientType(cli.getClientType())
                              .profileType(cli.getProfileType())
                              .dataCompany(cli.getDataCompany())
                              .dataPersonal(cli.getDataPersonal())
                              .debit(cli.getProduct().getDebit())
                              .build());
            });
  }


  /**
   * a.
   *
   * @param number a.
   * @return findProducByNumber
   */
  @Override
  public Mono<BalanceAccountResponse> findProducByNumber(String number, String producto) {
    if (producto.equals(Constante.TARJETA_CREDITO)) {
      return findProductByCreditCardNumber(number);
    }

    if (producto.equals(Constante.TARJETA_DEBITO)) {
      return findProductByDebitCardNumber(number);
    }

    if (producto.equals(Constante.CUENTA)) {
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

              List<BalanceDebitResponse> balanceDebitLst = x.getProduct().getDebit().stream()
                      .map(account -> new BalanceDebitResponse(account.getNumberCard(),
                                                               account.getBalance()))
                      .collect(Collectors.toList());

              BigDecimal accountTotal = x.getProduct().getAccount().stream()
                      .map(Account::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              BigDecimal creditTotal = x.getProduct().getCredit().stream()
                      .map(Credit::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              BigDecimal debitTotal = x.getProduct().getDebit().stream()
                      .map(Debit::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              return Mono.just(ProductBalanceResponse.builder()
                              .account(balanceAccountLst)
                              .credit(balanceCreditLst)
                              .debit(balanceDebitLst)
                              .totalBalance(accountTotal.add(creditTotal).add(debitTotal))
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
  public Mono<BalanceAccountResponse> findProductByCreditCardNumber(final String number) {
    return clientRepo.findCreditByCardNumber(number).flatMap(x -> {
      Optional<Credit> credit = x.getProduct().getCredit()
                 .stream()
                 .findFirst();

      return Mono.just(BalanceAccountResponse.builder()
                         .type(credit.get().getType())
                         .number(credit.get().getNumber())
                         .balance(credit.get().getBalance())
                 .build());
    });

  }

  /**
   * a.
   *
   * @param number a.
   * @return findProductByCardNumber
   */
  public Mono<BalanceAccountResponse> findProductByDebitCardNumber(final String number) {
    return clientRepo.findDebitByCardNumber(number).flatMap(x -> {
      Optional<Debit> debit = x.getProduct().getDebit()
              .stream()
              .findFirst();

      return Mono.just(BalanceAccountResponse.builder()
              .number(debit.get().getNumberCard())
              .balance(debit.get().getBalance())
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
