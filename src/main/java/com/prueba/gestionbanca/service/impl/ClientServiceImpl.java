package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.dto.ClientDto;
import com.prueba.gestionbanca.expose.request.ClientRequest;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Ubigeo;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ClientService;
import com.prueba.gestionbanca.util.Constante;
import java.util.Date;
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

    Ubigeo ubigeo = Ubigeo.builder()
           .userCreation(Constante.USER_MS)
           .dateCreation(new Date())
           .build();

    Client newClient;
    if (Objects.nonNull(clientReq.getDataPersonal())) {
      newClient = Client.builder()
                .clientType(clientReq.getClientType())
                .dataPersonal(clientReq.getDataPersonal())
                .email(clientReq.getEmail())
                .phono(clientReq.getPhono())
                .address(clientReq.getAddress())
                .product(clientReq.getProduct())
                .ubigeo(ubigeo)
                .build();
    } else {
      newClient = Client.builder()
                .clientType(clientReq.getClientType())
                .dataCompany(clientReq.getDataCompany())
                .email(clientReq.getEmail())
                .phono(clientReq.getPhono())
                .address(clientReq.getAddress())
                .product(clientReq.getProduct())
                .ubigeo(ubigeo)
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
               client.setUbigeo(Ubigeo.builder()
                       .userUpdate(Constante.USER_MS)
                       .dateUpdate(new Date())
                       .build());
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

  /**
   * a.
   *
   * @param number a.
   * @return Client
   */
  public Mono<Client> findProductCreditByCard(final String number) {
    return clientRepo.findByProductCreditCard(number);
  }


  /**
   * a.
   *
   * @param number a.
   * @return Client
   */
  public Mono<Client> findProductCreditByNumberAccount(final String number) {
    return clientRepo.findByProductCreditNumber(number);
  }
}
