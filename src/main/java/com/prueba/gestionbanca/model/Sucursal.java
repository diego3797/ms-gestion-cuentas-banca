package com.prueba.gestionbanca.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Sucursal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sucursal {

  private static final long serialVersionUID = 1L;

  private String id;

  private String name;

  private String address;

  private String nameOperator;

  private String nameManager;


}

