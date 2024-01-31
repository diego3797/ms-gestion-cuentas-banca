package com.prueba.gestionbanca.service;

import java.io.IOException;


/**
 * .
 * <b>Class</b>: ReportService <br/>
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
public interface ReportService {

  byte[] generateReportBalanceProducts(String documentId) throws IOException;

}
