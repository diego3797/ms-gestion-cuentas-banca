package com.prueba.gestionbanca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * DataPersonal
 */
@SuppressWarnings("unchecked")
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

