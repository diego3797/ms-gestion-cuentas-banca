package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ClientService <br/>
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
public interface ClientService {

    Mono<Client> addClient(ClientRequest client);

    Mono<Client> findClientById(String id);

    Flux<Client> findAll();

    Mono<Client> update(ClientDto client);

    Mono<Void> delete(String id);

    Mono<Client> findProductCreditByCard(String number);

    Mono<Client> findProductCreditByNumberAccount(String number);

}
