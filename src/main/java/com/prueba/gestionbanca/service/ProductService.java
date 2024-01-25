package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceMovementsResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductService <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 24, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
public interface ProductService {

    Mono<BalanceAccountResponse> findProducByNumber(String number);

    Mono<ProductBalanceResponse> findProductByDocumentNumber(String document);

    Mono<BalanceMovementsResponse> findMovementByNumberAccount(String number);

}
