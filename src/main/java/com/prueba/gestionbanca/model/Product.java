package com.prueba.gestionbanca.model;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Credit;
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
 * Product
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

  private static final long serialVersionUID = 1L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid Account> account;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid Credit> credit;

}

