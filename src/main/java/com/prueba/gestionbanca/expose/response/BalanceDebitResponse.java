package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * .
 * BalanceAccountResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDebitResponse {

  private static final long serialVersionUID = 1L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String card;

  private BigDecimal balance;

}

