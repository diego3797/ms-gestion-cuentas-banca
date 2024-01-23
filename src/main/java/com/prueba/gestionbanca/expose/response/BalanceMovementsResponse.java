package com.prueba.gestionbanca.expose.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.prueba.gestionbanca.model.Movements;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
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
 * BalanceMovementsResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceMovementsResponse {

  private static final long serialVersionUID = 1L;

  private String type;

  private String card;

  private String number;

  @Valid
  private List<@Valid Movements> movements;

  private BigDecimal balance;

}

