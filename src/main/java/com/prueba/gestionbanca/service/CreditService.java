package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.CreditRequest;
import com.prueba.gestionbanca.expose.response.CreditOperationResponse;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: CreditService <br/>
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
public interface CreditService {
  Mono<CreditOperationResponse> registerMovementCredit(CreditRequest payRequest,
                                                         String operacionCredit);
}
