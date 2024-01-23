package com.prueba.gestionbanca.model;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import org.springframework.data.annotation.CreatedDate;


import java.util.*;
import javax.annotation.Generated;

/**
 * Ubigeo
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ubigeo {

  private static final long serialVersionUID = 1L;

  private String userCreation;

  @CreatedDate
  private Date dateCreation;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String userUpdate;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Date dateUpdate;

}

