package com.prueba.gestionbanca.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * .
 * <b>Class</b>: Utils <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 24, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
public class Utils {

  private Utils() {
    throw new UnsupportedOperationException();
  }

  /**
   * a.
   *
   * @return generateNumber
   */
  public static int generateNumberOperation() {
    Random r = new Random(System.currentTimeMillis());
    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
  }

  /**
   * a.
   *
   * @return generateNumberCurrentAccount
   */
  public static long generateNumberCurrentAccount() {
    Random random = new Random();
    return 1000000000000L + (long) (random.nextDouble() * 9000000000000L);
  }

  /**
   * a.
   *
   * @return generateNumberAccount
   */
  public static long generateNumberAccount() {
    Random random = new Random();
    return 10000000000000L + (long) (random.nextDouble() * 90000000000000L);
  }

  /**
   * a.
   *
   * @return generateNumberAccount
   */
  public static int generateIdSucursal() {
    Random r = new Random(System.currentTimeMillis());
    return ((1 + r.nextInt(2)) * 100 + r.nextInt(100));
  }

  /**
   * a.
   *
   * @return convertDateToString
   */
  public static String convertDateToString(Date dt) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(dt);
  }



}
