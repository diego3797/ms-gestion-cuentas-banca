package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.service.OperationService;
import com.prueba.gestionbanca.util.EnumOperationType;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;



/**
 * .
 * <b>Class</b>: OperationController <br/>
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
@RestController
@RequestMapping("/operation")
@Slf4j
@RequiredArgsConstructor
public class OperationController {

  @Autowired
  private OperationService operationService;

  /**
   * .
   * POST /operation/deposit : Make deposit to count
   * Make deposit to count
   *
   * @param depositRequest Make deposit to count (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
            operationId = "registerDeposit",
            summary = "Make deposit to count",
            description = "Make deposit to count",
            tags = { "operation" },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountOperationResponse.class)),
                    @Content(mediaType = "application/xml",
                            schema = @Schema(implementation = AccountOperationResponse.class))
                }),
                @ApiResponse(responseCode = "405", description = "Invalid input")
            }
  )
  @RequestMapping(
          method = RequestMethod.PUT,
          value = "/deposit",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public ResponseEntity<Mono<AccountOperationResponse>> registerDeposit(
          @Parameter(name = "DepositRequest",
                  description = "Make deposit to count", required = true)
          @Valid @RequestBody MovementRequest depositRequest
  ) {
    return new ResponseEntity<Mono<AccountOperationResponse>>(
              operationService.registerMovementAccount(depositRequest, EnumOperationType.DEPOSITO),
            HttpStatus.OK);
  }


  /**
   * .
   * POST /operation/withdrawal : Make withdrawal to count
   * Make withdrawal to count
   *
   * @param withdrawalRequest Make withdrawal to count (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "registerWithdrawal",
          summary = "Make withdrawal to count",
          description = "Make withdrawal to count",
          tags = { "operation" },
          responses = {
              @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = AccountOperationResponse.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = AccountOperationResponse.class))
              }),
              @ApiResponse(responseCode = "405", description = "Invalid input")
          }
  )
  @RequestMapping(
          method = RequestMethod.PUT,
          value = "/withdrawal",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public ResponseEntity<Mono<AccountOperationResponse>> registerWithdrawal(
          @Parameter(name = "WithdrawalRequest",
                  description = "Make withdrawal to count", required = true)
          @Valid @RequestBody MovementRequest withdrawalRequest
  ) {
    return new ResponseEntity<Mono<AccountOperationResponse>>(
              operationService.registerMovementAccount(withdrawalRequest,
                                                        EnumOperationType.RETIRO),
            HttpStatus.OK);
  }
}
