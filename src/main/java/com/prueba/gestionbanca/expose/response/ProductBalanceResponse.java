package com.prueba.gestionbanca.expose.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * ProductBalanceResponse
 */

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

