package com.prueba.gestionbanca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * Address
 */

@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

  private static final long serialVersionUID = 1L;

  private String street;

  private String number;

  private String district;

  private String province;

  private String state;

}

