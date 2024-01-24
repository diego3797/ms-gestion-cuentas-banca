package com.prueba.gestionbanca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * DataCompany
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataCompany {

  private static final long serialVersionUID = 1L;

  private String ruc;

  private String name;

  private String dateCreationCompany;

}

