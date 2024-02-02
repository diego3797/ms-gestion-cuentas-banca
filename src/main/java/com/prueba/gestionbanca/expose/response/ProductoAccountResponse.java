package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.gestionbanca.model.Address;
import com.prueba.gestionbanca.model.DataCompany;
import com.prueba.gestionbanca.model.DataPersonal;
import com.prueba.gestionbanca.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * .
 * ProductBalanceResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoAccountResponse {

  private static final long serialVersionUID = 1L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String clientType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String profileType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataCompany dataCompany;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataPersonal dataPersonal;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Product product;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String msg;

}

