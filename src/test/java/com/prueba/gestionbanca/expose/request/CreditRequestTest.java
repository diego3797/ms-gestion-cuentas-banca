package com.prueba.gestionbanca.expose.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditRequestTest {

    @Test
    public void testNoArgsConstructor() {
        CreditRequest creditRequest = new CreditRequest();
        assertNotNull(creditRequest);
        assertNull(creditRequest.getCard());
        assertNull(creditRequest.getPayType());
        assertNull(creditRequest.getMount());
        assertNull(creditRequest.getMountConsume());
    }

    @Test
    public void testAllArgsConstructor() {
        String card = "123456789";
        String payType = "CreditCard";
        BigDecimal mount = BigDecimal.valueOf(1000);
        BigDecimal mountConsume = BigDecimal.valueOf(500);

        CreditRequest creditRequest = new CreditRequest(card, payType, mount, mountConsume);
        assertNotNull(creditRequest);
        assertEquals(card, creditRequest.getCard());
        assertEquals(payType, creditRequest.getPayType());
        assertEquals(mount, creditRequest.getMount());
        assertEquals(mountConsume, creditRequest.getMountConsume());
    }

}
