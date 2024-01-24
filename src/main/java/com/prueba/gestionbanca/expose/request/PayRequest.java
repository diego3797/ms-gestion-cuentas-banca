package com.prueba.gestionbanca.expose.request;

import com.prueba.gestionbanca.util.EnumPayType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * PayRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRequest {

  private static final long serialVersionUID = 1L;

  private String creditType;

  private String card;

  private EnumPayType payType;

  private BigDecimal mount;

}

