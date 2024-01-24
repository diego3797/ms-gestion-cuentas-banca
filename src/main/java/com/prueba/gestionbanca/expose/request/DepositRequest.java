package com.prueba.gestionbanca.expose.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
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
public class DepositRequest {

  private static final long serialVersionUID = 1L;

  private String typeAccount;

  private String numberAccount;

  private BigDecimal mount;

}

