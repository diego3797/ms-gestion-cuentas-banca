package com.prueba.gestionbanca.service;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.request.TransferRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.expose.response.TransferOperationResponse;
import com.prueba.gestionbanca.util.EnumOperationType;
import com.prueba.gestionbanca.util.EnumTransferType;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: TransferService <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 29, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
public interface TransferService {
  Mono<TransferOperationResponse> registerTransferAccount(TransferRequest transferRequest,
                                                            EnumTransferType transferType);
}
