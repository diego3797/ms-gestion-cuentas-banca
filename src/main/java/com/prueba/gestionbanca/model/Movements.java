package com.prueba.gestionbanca.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.gestionbanca.util.EnumOperationType;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * Movements
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movements {

  private static final long serialVersionUID = 1L;

  private String numberOperation;

  private Date dateOperation;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String numberAccountDestiny;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String numberAccountFrom;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nameTerceryAccount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String numberDocumentTerceryAccount;

  private EnumOperationType operationType;

  private BigDecimal mount;


}

