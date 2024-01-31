package com.prueba.gestionbanca.expose.response;

import com.prueba.gestionbanca.model.Movements;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * BalanceMovementsResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceMovementsResponse {

  private static final long serialVersionUID = 1L;

  private String type;

  private String card;

  private String number;

  @Valid
  private List<@Valid Movements> movements;

  private BigDecimal balance;

}

