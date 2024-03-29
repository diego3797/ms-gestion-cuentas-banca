package com.prueba.gestionbanca.expose.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

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

  @Valid
  private String card;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private String payType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private BigDecimal amount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private BigDecimal amountConsume;

}

