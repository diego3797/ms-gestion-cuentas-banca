package com.prueba.gestionbanca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * Sucursal
 */
@SuppressWarnings("unchecked")
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

