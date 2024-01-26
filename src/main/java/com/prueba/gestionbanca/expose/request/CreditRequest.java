package com.prueba.gestionbanca.expose.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * PayRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditRequest {

  private static final long serialVersionUID = 1L;

  private String card;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String payType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal mount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal mountConsume;

}

