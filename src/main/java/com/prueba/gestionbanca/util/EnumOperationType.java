package com.prueba.gestionbanca.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
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
  PAGO_MINIMO ("PAGO_MINIMO", 1),
  PAGO_TOTAL ("PAGO_TOTAL", 2),
  ABONO ("ABONO", 3),
  RETIRO ("RETIRO", 4),
  DEPOSITO ("DEPOSITO", 5),
  CONSUMO ("CONSUMO", 6);

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
              .findFirst().get();
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
              .findFirst().get();
  }



}
