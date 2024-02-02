package com.prueba.gestionbanca.expose.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
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
  private BigDecimal amount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal amountConsume;

}

