package com.prueba.gestionbanca.model;

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
 * Account
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

  private static final long serialVersionUID = 1L;

  private String type;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String card;

  private String number;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal dayMovement;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal comission;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid Movements> movements;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String dateCreation;

  private Sucursal sucursal;

  private BigDecimal balance;

}

