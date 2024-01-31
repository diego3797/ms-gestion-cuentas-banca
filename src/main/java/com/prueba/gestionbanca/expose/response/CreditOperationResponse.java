package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class CreditOperationResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String numberOperation;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String typeCredit;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String card;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal limitCredit;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal balance;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message;

}

