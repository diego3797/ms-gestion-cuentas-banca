package com.prueba.gestionbanca.model;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * Company
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid DataPersonal> titular;

  @Valid
  private List<@Valid DataPersonal> autorizedSignatory;


}

