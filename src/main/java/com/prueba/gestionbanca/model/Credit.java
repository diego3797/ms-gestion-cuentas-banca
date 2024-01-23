package com.prueba.gestionbanca.model;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.prueba.gestionbanca.model.Company;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.model.Sucursal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Credit
 */

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

