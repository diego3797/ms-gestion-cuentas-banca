package com.prueba.gestionbanca.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * .
 * ClientRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "client")
@Builder
public class Client {

  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  @Id
  private ObjectId id;

  private String clientType;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataCompany dataCompany;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DataPersonal dataPersonal;

  private String email;

  private String phono;

  private Address address;

  private List<Product> product;

  private Ubigeo ubigeo;
}
