package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.request.AccountRequest;
import com.prueba.gestionbanca.expose.request.MovementRequest;
import com.prueba.gestionbanca.expose.response.*;
import com.prueba.gestionbanca.service.ProductService;
import com.prueba.gestionbanca.util.EnumOperationType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * .
 * <b>Class</b>: ProductController <br/>
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
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
  @Autowired
  private ProductService prodService;

  /**
   * .
   * PUT /product/createAccount : Create account bank
   * Create account bank
   *
   * @param accountRequest Make deposit to count (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "registerAccount",
          summary = "Create account bank",
          description = "Create account bank",
          tags = { "product" },
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
  @PutMapping(value = "/createAccount",
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public Mono<ResponseEntity<ProductoAccountResponse>> registerAccount(
          @Parameter(name = "productAccountRequest",
                  description = "Create account bank", required = true)
          @Valid @RequestBody AccountRequest accountRequest
  ) {
    return prodService.registerProduct(accountRequest)
              .map(productoAccountResponse -> ResponseEntity.status(HttpStatus.OK)
                      .body(productoAccountResponse))
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * .
   * GET /product/balance/{numberDocument} : Gets balance all product of client
   * Gets balance all product of client
   *
   * @param numberDocument number document of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or product not found (status code 404)
   */
  @Operation(
          operationId = "getAllProduct",
          summary = "Gets balance all product of client",
          description = "Gets balance all product of client",
          tags = { "product" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = ProductBalanceResponse.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = ProductBalanceResponse.class))
              }),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "product not found")
          }
  )
  @GetMapping(
          value =  "/balance/{numberDocument}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<ProductBalanceResponse>> getAllProduct(
          @Parameter(name = "numberDocument",
                  description = "number document of client", required = true, in = ParameterIn.PATH)
          @PathVariable("numberDocument") String numberDocument
  ) {
    return prodService.findProductByDocumentNumber(numberDocument)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }


  /**
   * .
   * GET /product/balance/{number} : Gets balance of client account or credit card
   * Gets balance of client account or credit card
   *
   * @param number Account number or Credit number of product of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or product not found (status code 404)
   */
  @Operation(
          operationId = "getBalanceProduct",
          summary = "Gets balance of client account or credit card",
          description = "Gets balance of client account or credit card",
          tags = { "product" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = BalanceAccountResponse.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = BalanceAccountResponse.class))
              }),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "product not found")
          }
  )
  @GetMapping(
          value =  "/balance/account/{number}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<BalanceAccountResponse>> getBalanceProduct(
          @Parameter(name = "number",
                  description = "Account number or Credit number of product of client",
                  required = true, in = ParameterIn.PATH)
          @PathVariable("number") final String number
  ) {

    return prodService.findProducByNumber(number)
              .map(ResponseEntity::ok)
              .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

  }


  /**
   * .
   * GET /product/movements/{number} : Gets the movements of client account or credit card
   * Gets the movements of client account or credit card
   *
   * @param number Account number or Credit number of product of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or client not foundg (status code 404)
   */
  @Operation(
          operationId = "getMovements",
          summary = "Gets the movements of client account or credit card",
          description = "Gets the movements of client account or credit card",
          tags = { "product" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = BalanceMovementsResponse.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = BalanceMovementsResponse.class))
              }),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "client not foundg")
          }
  )
  @GetMapping(
          value = "/movements/{number}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<BalanceMovementsResponse>> getMovements(
          @Parameter(name = "number",
                  description = "Account number or Credit number of product of client",
                  required = true, in = ParameterIn.PATH)
          @PathVariable("number") String number
  ) {
    return prodService.findMovementByNumberAccount(number)
              .map(ResponseEntity::ok)
              .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

}
