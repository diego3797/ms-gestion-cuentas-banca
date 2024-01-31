package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.util.EnumOperationType;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: OperationService <br/>
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
public interface OperationService {

  Mono<AccountOperationResponse> registerMovementAccount(MovementRequest depositRequest,
                                                         EnumOperationType operation);

}
