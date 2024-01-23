package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceMovementsResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
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

    /**
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductBalanceResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductBalanceResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "product not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/balance/{numberDocument}",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<Mono<ProductBalanceResponse>> getAllProduct(
            @Parameter(name = "numberDocument", description = "number document of client", required = true, in = ParameterIn.PATH)
            @PathVariable("numberDocument") String numberDocument
    ) {
        return null;
    }


    /**
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceAccountResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = BalanceAccountResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "product not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/balance/{number}",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<Mono<BalanceAccountResponse>> getBalanceProduct(
            @Parameter(name = "number", description = "Account number or Credit number of product of client", required = true, in = ParameterIn.PATH)
            @PathVariable("number") String number
    ) {
        return null;
    }


    /**
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceMovementsResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = BalanceMovementsResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "client not foundg")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/product/movements/{number}",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<Mono<BalanceMovementsResponse>> getMovements(
            @Parameter(name = "number", description = "Account number or Credit number of product of client", required = true, in = ParameterIn.PATH)
            @PathVariable("number") String number
    ) {
        return null;
    }

}
