package com.prueba.gestionbanca.expose.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * AccountOperationResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountOperationResponse {
  private String numberOperation;

  private String typeAccount;

  private String card;

  private String number;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal comision;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal grossAmount;

  private BigDecimal amount;

  private BigDecimal balanceTotal;

}
