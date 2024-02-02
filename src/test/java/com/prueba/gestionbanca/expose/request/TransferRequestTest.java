package com.prueba.gestionbanca.expose.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransferRequestTest {

    @Test
    public void testNoArgsConstructor() {
        TransferRequest transferRequest = new TransferRequest();
        assertNotNull(transferRequest);
        assertNull(transferRequest.getNumberAccountFrom());
        assertNull(transferRequest.getNumberDocumentTercery());
        assertNull(transferRequest.getNumberAccountDestiny());
        assertNull(transferRequest.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        String numberAccountFrom = "123456789";
        String numberDocumentTercery = "987654321";
        String numberAccountDestiny = "987654321";
        BigDecimal mount = BigDecimal.valueOf(1000);

        TransferRequest transferRequest = new TransferRequest(numberAccountFrom, numberDocumentTercery, numberAccountDestiny, mount);
        assertNotNull(transferRequest);
        assertEquals(numberAccountFrom, transferRequest.getNumberAccountFrom());
        assertEquals(numberDocumentTercery, transferRequest.getNumberDocumentTercery());
        assertEquals(numberAccountDestiny, transferRequest.getNumberAccountDestiny());
        assertEquals(mount, transferRequest.getAmount());
    }
}
