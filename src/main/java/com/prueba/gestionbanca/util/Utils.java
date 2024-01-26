package com.prueba.gestionbanca.util;

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

    /**
     * a.
     *
     * @return generateNumber
     */
    public static int generateNumber() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
