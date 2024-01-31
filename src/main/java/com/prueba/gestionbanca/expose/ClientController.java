package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: ClientController <br/>
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
@RequestMapping("/client")
public class ClientController {
  @Autowired
  private ClientService clientService;

  /**
   * .
   * POST /client : Add a new client in the bank
   * Add a new client in the bank
   *
   * @param clientRequest Create a new client in the bank (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
            operationId = "addClient",
            summary = "Add a new client in the bank",
            description = "Add a new client in the bank",
            tags = { "client" },
            responses = {
              @ApiResponse(responseCode = "201", description = "Successful operation",
                            content = {
                              @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Client.class)),
                              @Content(mediaType = "application/xml",
                                    schema = @Schema(implementation = Client.class))
                            }),
              @ApiResponse(responseCode = "405", description = "Invalid input")
            }
  )
  @PostMapping(
          produces = { "application/json", "application/xml" },
          consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
  )
  public Mono<ResponseEntity<Client>> addClient(@Valid @RequestBody ClientRequest clientRequest) {
    return clientService.addClient(clientRequest)
              .map(client -> ResponseEntity.status(HttpStatus.CREATED).body(client));
  }


  /**
   * .
   * DELETE /client/{clientId} : Deletes a client
   * delete a client
   *
   * @param clientId Client id to delete (required)
   */
  @Operation(
          operationId = "deleteClient",
          summary = "Deletes a client",
          description = "delete a client",
          tags = { "client" },
          responses = {
              @ApiResponse(responseCode = "400", description = "Invalid client value")
          }
  )
  @DeleteMapping("/{clientId}")
  public Mono<ResponseEntity<Void>> deleteClient(
          @Parameter(name = "clientId", description = "Client id to delete",
                  required = true, in = ParameterIn.PATH)
          @PathVariable("clientId") String clientId
  ) {
    return clientService.delete(clientId)
              .thenReturn(ResponseEntity.noContent().<Void>build())
              .onErrorReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }


  /**
   * .
   * GET /client/{clientId} : List all clients from bank
   * List all clients from bank
   *
   * @param clientId ID of client to return (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or client not found (status code 404)
   */
  @Operation(
          operationId = "getClientById",
          summary = "Get clients by id from bank",
          description = "Get clients by id from bank",
          tags = { "client" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Client.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = Client.class))
              }),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "client not found")
          }
  )
  @GetMapping(
          value = "/{clientId}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<Client>> getClientById(
          @Parameter(name = "clientId", description = "ID of client to return",
                  required = true, in = ParameterIn.PATH)
          @PathVariable("clientId") String clientId
  ) {
    return clientService.findClientById(clientId)
              .map(client -> ResponseEntity.status(HttpStatus.OK).body(client));
  }


  /**
   * .
   * GET /client/list : List all clients from bank
   * List all clients from bank
   *
   * @return successful operation (status code 200)
   */
  @Operation(
          operationId = "getClients",
          summary = "List all clients from bank",
          description = "List all clients from bank",
          tags = { "client" },
          responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                          content = @Content(mediaType = "application/json",
                                  schema = @Schema(implementation = Client.class)))
          }
  )
  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Client> getClients() {
    return clientService.findAll();
  }


  /**
   * .
   * PUT /client : Update a client in the bank
   * Update a client in the bank
   *
   * @param clientRequest Update a client in the bank (required)
   * @return Successful operation (status code 200)
   *         or Invalid input (status code 405)
   */
  @Operation(
          operationId = "updClient",
          summary = "Update a client in the bank",
          description = "Update a client in the bank",
          tags = { "client" },
          responses = {
              @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                  @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Client.class)),
                  @Content(mediaType = "application/xml",
                          schema = @Schema(implementation = Client.class))
              }),
              @ApiResponse(responseCode = "405", description = "Invalid input")
          }
  )
  @PutMapping(produces = { "application/json", "application/xml" },
             consumes = { "application/json",
                            "application/xml",
                            "application/x-www-form-urlencoded"
             }
  )
  public Mono<ResponseEntity<Client>> updClient(
          @Parameter(name = "ClientRequest", description = "Update a client in the bank",
                  required = true)
          @Valid @RequestBody ClientDto clientRequest
  ) {
    return clientService.update(clientRequest)
              .map(client -> ResponseEntity.status(HttpStatus.OK).body(client));
  }

}
