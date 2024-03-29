package com.prueba.gestionbanca.expose.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * .
 * DepositRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementRequest {

  private static final long serialVersionUID = 1L;

  @Valid
  private String numberAccount;

  @Valid
  private BigDecimal amount;

}

