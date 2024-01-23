package com.prueba.gestionbanca.expose.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
