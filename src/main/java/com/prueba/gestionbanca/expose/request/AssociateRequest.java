package com.prueba.gestionbanca.expose.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * .
 * ClientRequest
 */
@SuppressWarnings("unchecked")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssociateRequest {

  private static final long serialVersionUID = 1L;

  @Valid
  private String documentNumber;

  @Valid
  private String debitCard;

  @Valid
  private AssociateAccountRequest accountAssociate;
}
