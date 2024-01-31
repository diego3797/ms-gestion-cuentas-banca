package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.request.CreditRequest;
import com.prueba.gestionbanca.expose.response.CreditOperationResponse;
import com.prueba.gestionbanca.service.CreditService;
import com.prueba.gestionbanca.util.Constante;
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
 * <b>Class</b>: CreditController <br/>
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
@RequestMapping("/credit")
@Slf4j
@RequiredArgsConstructor
public class CreditController {

  @Autowired
  private CreditService creditService;

  /**
  * .
  * POST /credit/consume : Make consume of credit
  * Make consume of credit
  *
  * @param consumeRequest Make consume of credit (required)
  * @return Successful operation (status code 200)
  *         or Invalid input (status code 405)
  */
  @Operation(
            operationId = "registerConsume",
            summary = "Make consume of credit",
            description = "Make consume of credit",
            tags = { "credit" },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json",
                             schema = @Schema(implementation = CreditOperationResponse.class)),
                    @Content(mediaType = "application/xml",
                             schema = @Schema(implementation = CreditOperationResponse.class))
                }),
                @ApiResponse(responseCode = "405", description = "Invalid input")
            }
    )
  @PutMapping(value = "/consume",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
  public Mono<ResponseEntity<CreditOperationResponse>> registerConsume(
          @Parameter(name = "ConsumeRequest",
                  description = "Make consume of credit", required = true)
          @Valid @RequestBody CreditRequest consumeRequest
  ) {
    return creditService.registerMovementCredit(consumeRequest, Constante.CONSUMO_CREDIT)
              .map(creditOperationResponse -> ResponseEntity.status(HttpStatus.OK)
                                                    .body(creditOperationResponse))
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  /**
   * .
   * POST /credit/pay : Make pay of credit
   * Make pay of credit
   *
   * @param payRequest Make pay of credit (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "registerPay",
          summary = "Make pay of credit",
          description = "Make pay of credit",
          tags = { "credit" },
          responses = {
              @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                  @Content(mediaType = "application/json",
                         schema = @Schema(implementation = CreditOperationResponse.class)),
                  @Content(mediaType = "application/xml",
                         schema = @Schema(implementation = CreditOperationResponse.class))
              }),
              @ApiResponse(responseCode = "405", description = "Invalid input")
          }
  )
  @PutMapping(value = "/pay",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
  public Mono<ResponseEntity<CreditOperationResponse>> registerPay(
          @Parameter(name = "PayRequest", description = "Make pay of credit", required = true)
          @Valid @RequestBody CreditRequest payRequest
  ) {
    return creditService.registerMovementCredit(payRequest, Constante.PAGO_CREDIT)
              .map(creditOperationResponse -> ResponseEntity.status(HttpStatus.OK)
                      .body(creditOperationResponse))
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }


}
