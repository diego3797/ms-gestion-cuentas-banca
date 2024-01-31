package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.request.TransferRequest;
import com.prueba.gestionbanca.expose.response.TransferOperationResponse;
import com.prueba.gestionbanca.service.TransferService;
import com.prueba.gestionbanca.util.EnumTransferType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;



/**
 * .
 * <b>Class</b>: TransferController <br/>
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
@RestController
@RequestMapping("/transfer")
@Slf4j
@RequiredArgsConstructor
public class TransferController {

  @Autowired
  private TransferService transferService;

  /**
   * .
   * PUT /transfer/toSelfAccount : transfer to your own account
   * transfer to your own account
   *
   * @param transferRequest transfer to your own account (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "registerTransftoAccountSelf",
          summary = "transfer to your own account",
          description = "transfer to your own account",
          tags = { "operation" },
          responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json",
                        schema = @Schema(implementation = TransferOperationResponse.class)),
                @Content(mediaType = "application/xml",
                        schema = @Schema(implementation = TransferOperationResponse.class))
            }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
          }
  )
  @PutMapping(value = "/toSelfAccount",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public Mono<ResponseEntity<TransferOperationResponse>> registerTransftoAccountSelf(
          @Parameter(name = "TransferRequest",
                  description = "transfer to your own account", required = true)
          @Valid @RequestBody TransferRequest transferRequest
  ) {
    return transferService.registerTransferAccount(transferRequest, EnumTransferType.CUENTA_PROPIA)
              .map(transferOperationResponse -> ResponseEntity.status(HttpStatus.OK)
                      .body(transferOperationResponse))
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  /**
   * .
   * PUT /transfer/toAccountThird : transfer to the account of a third party
   * transfer to the account of a third party
   *
   * @param transferRequest transfer to the account of a third party (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "registerTransftoAccountThird",
          summary = "transfer to the account of a third party",
          description = "transfer to the account of a third party",
          tags = { "operation" },
          responses = {
              @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = TransferOperationResponse.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = TransferOperationResponse.class))
              }),
              @ApiResponse(responseCode = "405", description = "Invalid input")
          }
  )
  @PutMapping(value = "/toAccountThird",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public Mono<ResponseEntity<TransferOperationResponse>> registerTransftoAccountThird(
          @Parameter(name = "TransferRequest",
                  description = "transfer to the account of a third party", required = true)
          @Valid @RequestBody TransferRequest transferRequest
  ) {
    return transferService.registerTransferAccount(transferRequest, EnumTransferType.CUENTA_TERCERO)
              .map(transferOperationResponse -> ResponseEntity.status(HttpStatus.OK)
                      .body(transferOperationResponse))
              .defaultIfEmpty(ResponseEntity.notFound().build());

  }

}
