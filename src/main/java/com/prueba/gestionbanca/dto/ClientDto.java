package com.prueba.gestionbanca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.prueba.gestionbanca.model.Address;
import com.prueba.gestionbanca.model.DataCompany;
import com.prueba.gestionbanca.model.DataPersonal;
import com.prueba.gestionbanca.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * .
 * ClientDto
 */
@SuppressWarnings({"java:S106", "all", "all"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  @Id
  private ObjectId id; // NOSONAR

  private String clientType; // NOSONAR

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataCompany dataCompany; // NOSONAR

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataPersonal dataPersonal; // NOSONAR

  private String email; // NOSONAR

  private String phono; // NOSONAR

  private Address address; // NOSONAR

  private Product product; // NOSONAR

}
