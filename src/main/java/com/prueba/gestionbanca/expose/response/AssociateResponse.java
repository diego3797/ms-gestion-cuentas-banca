package com.prueba.gestionbanca.expose.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.DataCompany;
import com.prueba.gestionbanca.model.DataPersonal;
import java.util.List;
import javax.validation.Valid;

import com.prueba.gestionbanca.model.Debit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * BalanceAccountResponse
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociateResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String clientType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String profileType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataCompany dataCompany;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataPersonal dataPersonal;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Valid
  private List<@Valid Debit> debit;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String msg;

}
