package com.prueba.gestionbanca.expose.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * BalanceAccountResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceAccountResponse {

  private static final long serialVersionUID = 1L;

  private String type;

  private String card;

  private String number;

  private BigDecimal balance;

}

