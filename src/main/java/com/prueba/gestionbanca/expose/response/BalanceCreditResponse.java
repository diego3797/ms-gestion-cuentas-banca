package com.prueba.gestionbanca.expose.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * BalanceCreditResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceCreditResponse {

  private static final long serialVersionUID = 1L;

  private String type;

  private String card;

  private String number;

  private BigDecimal limitCredit;

  private BigDecimal balance;

}

