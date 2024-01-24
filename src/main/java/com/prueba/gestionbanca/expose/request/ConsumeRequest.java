package com.prueba.gestionbanca.expose.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * ConsumeRequest
 */

@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumeRequest {

  private static final long serialVersionUID = 1L;

  private String creditType;

  private String card;

  private BigDecimal mountConsume;

}

