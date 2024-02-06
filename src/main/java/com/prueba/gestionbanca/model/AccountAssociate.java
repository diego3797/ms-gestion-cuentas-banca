package com.prueba.gestionbanca.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * Account
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountAssociate {

  private String accountNumber;

  private Date dateAssociate;

}
