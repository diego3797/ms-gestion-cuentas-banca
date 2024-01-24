package com.prueba.gestionbanca.expose.request;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * WithdrawalRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String typeAccount;

  private String numberAccount;

  private BigDecimal mount;

}

