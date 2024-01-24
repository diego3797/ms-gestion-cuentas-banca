package com.prueba.gestionbanca.expose.response;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * ProductBalanceResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBalanceResponse  {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid BalanceAccountResponse> account;

  @Valid
  private List<@Valid BalanceCreditResponse> credit;

  private BigDecimal totalBalance;

}

