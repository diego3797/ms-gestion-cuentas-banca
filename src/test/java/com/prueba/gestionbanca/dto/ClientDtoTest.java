package com.prueba.gestionbanca.dto;

import com.prueba.gestionbanca.model.Address;
import com.prueba.gestionbanca.model.DataCompany;
import com.prueba.gestionbanca.model.DataPersonal;
import com.prueba.gestionbanca.model.Product;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientDtoTest {
    @Test
    public void testConstructorAndGetters() {
        ObjectId id = new ObjectId();
        String clientType = "exampleClientType";
        DataCompany dataCompany = new DataCompany();
        DataPersonal dataPersonal = new DataPersonal();
        String email = "example@example.com";
        String phone = "123456789";
        Address address = new Address();
        Product product = new Product();

        ClientDto clientDto = new ClientDto(id, clientType, dataCompany, dataPersonal,
                email, phone, address, product);

        assertEquals(id, clientDto.getId());
        assertEquals(clientType, clientDto.getClientType());
        assertEquals(dataCompany, clientDto.getDataCompany());
        assertEquals(dataPersonal, clientDto.getDataPersonal());
        assertEquals(email, clientDto.getEmail());
        assertEquals(phone, clientDto.getPhono());
        assertEquals(address, clientDto.getAddress());
        assertEquals(product, clientDto.getProduct());
    }

    @Test
    public void testBuilder() {
        ObjectId id = new ObjectId();
        String clientType = "exampleClientType";
        DataCompany dataCompany = new DataCompany();
        DataPersonal dataPersonal = new DataPersonal();
        String email = "example@example.com";
        String phone = "123456789";
        Address address = new Address();
        Product product = new Product();

        ClientDto clientDto = ClientDto.builder()
                .id(id)
                .clientType(clientType)
                .dataCompany(dataCompany)
                .dataPersonal(dataPersonal)
                .email(email)
                .phono(phone)
                .address(address)
                .product(product)
                .build();

        assertEquals(id, clientDto.getId());
        assertEquals(clientType, clientDto.getClientType());
        assertEquals(dataCompany, clientDto.getDataCompany());
        assertEquals(dataPersonal, clientDto.getDataPersonal());
        assertEquals(email, clientDto.getEmail());
        assertEquals(phone, clientDto.getPhono());
        assertEquals(address, clientDto.getAddress());
        assertEquals(product, clientDto.getProduct());
    }
}
