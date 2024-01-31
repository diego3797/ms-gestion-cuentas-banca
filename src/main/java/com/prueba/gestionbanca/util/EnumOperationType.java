package com.prueba.gestionbanca.util;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * .
 * <b>Enum</b>: EnumOperationType <br/>
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
@Getter
@RequiredArgsConstructor
public enum EnumOperationType {
  PAGO_MINIMO("PAGO_MINIMO", 1),
  PAGO_TOTAL("PAGO_TOTAL", 2),
  RETIRO("RETIRO", 3),
  DEPOSITO("DEPOSITO", 4),
  CONSUMO("CONSUMO", 5),
  TRANSF_CUENTA_PROPIA("TRANSF_CUENTA_PROPIA", 6),
  TRANSF_CUENTA_TERCERO("TRANSF_CUENTA_TERCERO", 7),
  DEPOSITO_TRANSF_PROPIA("DEPOSITO_TRANSF_PROPIA", 8),
  DEPOSITO_TRANSF_TERCERO("DEPOSITO_TRANSF_TERCERO", 9);


  private final String nombre;
  private final int code;

  /**
   * a.
   *
   * @param code a.
   * @return getByCode
   */
  public static EnumOperationType getByCode(int code) {
    return Arrays.stream(EnumOperationType.values())
              .filter(enumOperationType -> enumOperationType.getCode() == code)
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException(
                      "No EnumOperationType found with code: " + code));
  }

  /**
   * a.
   *
   * @param nombre a.
   * @return getByNombre
   */
  public static EnumOperationType getByNombre(String nombre) {
    return Arrays.stream(EnumOperationType.values())
              .filter(enumOperationType -> enumOperationType.getNombre().equals(nombre))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException(
                      "No EnumOperationType found with code: " + nombre));
  }



}
