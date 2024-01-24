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
 * Credit
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {

  private static final long serialVersionUID = 1L;

  private String type;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String card;

  private String number;

  private BigDecimal limitCredit;

  @Valid
  private List<@Valid Movements> movements = new ArrayList<>();

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private Company company;

  private String dateCreation;

  private Sucursal sucursal;

  private BigDecimal balance;


}

