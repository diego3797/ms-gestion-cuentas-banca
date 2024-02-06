package com.prueba.gestionbanca.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class Debit {

  private static final long serialVersionUID = 1L;

  private String numberCard;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid AccountAssociate> associatedAccount;

  @Valid
  private List<@Valid Movements> movements = new ArrayList<>();

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String dateCreation;

  private Sucursal sucursal;

  private BigDecimal balance;

}

