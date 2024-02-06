package com.prueba.gestionbanca.expose.request;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class AssociateAccountRequest {

  @Valid
  private String accountNumber;

  @Valid
  private String acountType;

}
