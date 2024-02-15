package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ClientService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * a.
 * <b>Class</b>: ClientServiceImpl <br/>
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

@Service
public class ClientServiceImpl implements ClientService {

  /**
   * a.
   */
  @Autowired
  private ClientRepository clientRepo;

  /**
  * a.

  * @return Client
  */
  @Override
  public Mono<Client> addClient(final ClientRequest clientReq) {

    Client newClient;
    if (Objects.nonNull(clientReq.getDataPersonal())) {
      newClient = Client.builder()
                .clientType(clientReq.getClientType())
                .dataPersonal(clientReq.getDataPersonal())
                .email(clientReq.getEmail())
                .phono(clientReq.getPhono())
                .address(clientReq.getAddress())
                .product(clientReq.getProduct())
                .build();
    } else {
      newClient = Client.builder()
                .clientType(clientReq.getClientType())
                .dataCompany(clientReq.getDataCompany())
                .email(clientReq.getEmail())
                .phono(clientReq.getPhono())
                .address(clientReq.getAddress())
                .product(clientReq.getProduct())
                .build();
    }

    if (Objects.nonNull(newClient)) {
      return clientRepo.save(newClient);
    } else {
      return null;
    }

  }


  /**
  * a.

  * @return Client
  */
  @Override
  public Mono<Client> findClientById(final String id) {
    return clientRepo.findById(id);
  }

  /**
  * a.

  * @return Client
  */
  @Override
  public Flux<Client> findAll() {
    return clientRepo.findAll();
  }

  /**
  * a.

  * @return Client
  */
  @Override
  public Mono<Client> update(final ClientDto clientReq) {
    return clientRepo.findById(clientReq.getId())
             .flatMap(client -> {
               client.setClientType(clientReq.getClientType());
               client.setDataPersonal(clientReq.getDataPersonal());
               client.setDataCompany(clientReq.getDataCompany());
               client.setEmail(clientReq.getEmail());
               client.setPhono(clientReq.getPhono());
               client.setAddress(clientReq.getAddress());
               client.setProduct(clientReq.getProduct());
               return clientRepo.save(client);
             });
  }

  /**
  * a.
  * void
  */
  @Override
  public Mono<Void> delete(final String id) {
    return clientRepo.deleteById(id);
  }

}
