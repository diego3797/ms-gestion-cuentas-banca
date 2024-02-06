package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.AccountRequest;
import com.prueba.gestionbanca.expose.request.AssociateRequest;
import com.prueba.gestionbanca.expose.response.*;
import reactor.core.publisher.Mono;

/**
 * .
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

  Mono<ProductoAccountResponse> registerProduct(AccountRequest number);

  Mono<AssociateResponse> associateDebitCard(AssociateRequest associateRequest);

  Mono<BalanceAccountResponse> findProducByNumber(String number, String producto);

  Mono<ProductBalanceResponse> findProductByDocumentNumber(String document);

  Mono<BalanceMovementsResponse> findMovementByNumberAccount(String number);

}
