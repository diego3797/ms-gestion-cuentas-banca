package com.prueba.gestionbanca.expose.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * .
 * AccountOperationResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperationResponse {
    private String numberOperation;

    private String typeAccount;

    private String card;

    private String number;

    private BigDecimal balance;
}