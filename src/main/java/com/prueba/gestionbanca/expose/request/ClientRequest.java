package com.prueba.gestionbanca.expose.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.gestionbanca.model.Address;
import com.prueba.gestionbanca.model.DataCompany;
import com.prueba.gestionbanca.model.DataPersonal;
import com.prueba.gestionbanca.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * .
 * ClientRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequest {

  private static final long serialVersionUID = 1L;

  private String clientType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataCompany dataCompany;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataPersonal dataPersonal;

  private String email;

  private String phono;

  private Address address;

  @Valid
  private List<@Valid Product> product = new ArrayList<>();

}

