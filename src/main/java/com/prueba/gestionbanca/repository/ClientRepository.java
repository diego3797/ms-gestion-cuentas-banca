package com.prueba.gestionbanca.repository;

import com.prueba.gestionbanca.model.Client;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: ClientRepository <br/>
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
@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Object> {
  @Query(value = "{ 'product.account.number': ?0 }", fields = "{ 'product.account.$': 1 }")
  Mono<Client> findAccountByNumber(String number);

  @Query(value = "{ 'product.credit.card': ?0 }", fields = "{ 'product.credit.$': 1 }")
  Mono<Client> findCreditByNumber(String number);

  @Query(value = "{ $or : [{\"dataPersonal.documentNumber\" : ?0} , {\"dataCompany.ruc\" : ?0}] }",
         fields = "{ _id: 0,"
                + " dataPersonal: 0,\n"
                + " dataCompany: 0,\n"
                + " email: 0,\n"
                + " phono: 0,\n"
                + " address: 0  }")
  Mono<Client> findProductsByDocumentNumber(String document);

  @Query(value = "{ 'product.account.number': ?0 }")
  Mono<Client> findClientByNumberAccount(String number);

  @Query(value = "{ 'product.account.number': ?0 }", fields = "{  clientType: 1,"
                                                              + " profileType: 1,"
                                                              + " dataPersonal: 1,"
                                                              + " dataCompany: 1,"
                                                              + "'product.account.$': 1 }")
  Mono<Client> findAccountByNumberWithDataClient(String number);

}


