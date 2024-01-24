package com.prueba.gestionbanca.model;

import com.prueba.gestionbanca.util.EnumOperationType;
import java.math.BigDecimal;
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

  private String dateOperation;

  private EnumOperationType operationType;

  private BigDecimal mount;


}

