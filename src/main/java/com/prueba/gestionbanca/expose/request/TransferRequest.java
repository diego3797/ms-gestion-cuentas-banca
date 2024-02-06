package com.prueba.gestionbanca.expose.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * DepositRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequest {

  private static final long serialVersionUID = 1L;

  @Valid
  private String numberAccountFrom;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private String numberDocumentTercery;

  @Valid
  private String numberAccountDestiny;

  @Valid
  private BigDecimal amount;

}

