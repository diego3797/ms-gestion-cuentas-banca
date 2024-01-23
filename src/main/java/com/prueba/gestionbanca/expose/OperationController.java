package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.expose.request.DepositRequest;
import com.prueba.gestionbanca.expose.request.WithdrawalRequest;
import com.prueba.gestionbanca.expose.response.AccountOperationResponse;
import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
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


    /**
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountOperationResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = AccountOperationResponse.class))
                    }),
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/deposit",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    public ResponseEntity<Mono<AccountOperationResponse>> registerDeposit(
            @Parameter(name = "DepositRequest", description = "Make deposit to count", required = true)
            @Valid @RequestBody Mono<DepositRequest> depositRequest
    ) {
        return null;
    }


    /**
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountOperationResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = AccountOperationResponse.class))
                    }),
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/withdrawal",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    public ResponseEntity<Mono<AccountOperationResponse>> registerWithdrawal(
            @Parameter(name = "WithdrawalRequest", description = "Make withdrawal to count", required = true)
            @Valid @RequestBody Mono<WithdrawalRequest> withdrawalRequest
    ) {
        return null;
    }
}
