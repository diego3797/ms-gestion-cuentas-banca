package com.prueba.gestionbanca.repository;

import com.prueba.gestionbanca.model.Client;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
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
    @Query(value = "{'product.credit.number': ?0}")
    Mono<Client> findByProductCreditNumber(String creditNumber);

    @Query(value = "{'product.credit.card': ?0}")
    Mono<Client> findByProductCreditCard(String cardNumber);
}
