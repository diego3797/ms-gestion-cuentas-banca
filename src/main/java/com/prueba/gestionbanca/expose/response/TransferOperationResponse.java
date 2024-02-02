package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
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
public class TransferOperationResponse {
  private String numberOperation;

  private String typeAccountFrom;

  private String numberAccountFrom;

  private String typeAccountDestiny;

  private String numberAccountDestiny;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String numberDocumentThird;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nameThird;

  private BigDecimal amountTransfer;
}
