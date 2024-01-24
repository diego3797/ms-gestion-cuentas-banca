package com.prueba.gestionbanca.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

/**
 * .
 * Ubigeo
 */
@SuppressWarnings("unchecked")
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

