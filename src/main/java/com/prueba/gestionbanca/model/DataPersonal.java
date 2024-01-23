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
 * DataPersonal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataPersonal  {

  private static final long serialVersionUID = 1L;

  private String documentType;

  private String documentNumber;

  private String name;

  private String lastFather;

  private String lastMother;

  private String birthdate;

}

