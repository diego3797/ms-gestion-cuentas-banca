package com.prueba.gestionbanca.expose.response;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * CreditOperationResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditOperationResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String numberOperation;

  private String typeCredit;

  private String card;

  private BigDecimal limitCredit;

  private BigDecimal balance;

}

