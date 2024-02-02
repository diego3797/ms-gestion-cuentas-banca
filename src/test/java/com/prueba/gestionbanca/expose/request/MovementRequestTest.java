package com.prueba.gestionbanca.expose.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MovementRequestTest {
    @Test
    public void testNoArgsConstructor() {
        MovementRequest movementRequest = new MovementRequest();
        assertNotNull(movementRequest);
        assertNull(movementRequest.getNumberAccount());
        assertNull(movementRequest.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        String numberAccount = "123456789";
        BigDecimal mount = BigDecimal.valueOf(1000);

        MovementRequest movementRequest = new MovementRequest(numberAccount, mount);
        assertNotNull(movementRequest);
        assertEquals(numberAccount, movementRequest.getNumberAccount());
        assertEquals(mount, movementRequest.getAmount());
    }
}
