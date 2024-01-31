package com.prueba.gestionbanca.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>: Constante <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 22, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@SuppressWarnings("java:S106")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constante {

  public static final String USER_MS = "API-MS-BANK";

  public static final Integer DIGITS_CARD = 16;
  public static final Integer DIGITS_ACCOUNT_CTACORRIENTE = 13;
  public static final Integer DIGITS_ACCOUNT_AHORRO = 14;

  public static final String PAGO_CREDIT = "PAGO_CREDIT";
  public static final String CONSUMO_CREDIT = "CONSUMO_CREDIT";

}
