package com.prueba.gestionbanca.expose.request;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;

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
 * DepositRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {

  private static final long serialVersionUID = 1L;

  private String typeAccount;

  private String numberAccount;

  private BigDecimal mount;

}

