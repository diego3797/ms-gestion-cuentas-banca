package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ProductBalanceResponse  {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid BalanceAccountResponse> account;

  @Valid
  private List<@Valid BalanceCreditResponse> credit;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid BalanceDebitResponse> debit;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal totalBalanceAccount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal totalBalanceCredit;

  private BigDecimal totalBalance;

}

