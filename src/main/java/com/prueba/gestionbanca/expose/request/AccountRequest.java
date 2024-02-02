package com.prueba.gestionbanca.expose.request;

import java.math.BigDecimal;

import com.prueba.gestionbanca.model.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;


/**
 * .
 * AccountRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

  private static final long serialVersionUID = 1L;

  @Valid
  private String numberDocument;

  @Valid
  private String clientType;

  @Valid
  private String profileType;

  @Valid
  private String accountType;

  @Valid
  private Sucursal sucursal;

  @Valid
  private BigDecimal amount;

}

