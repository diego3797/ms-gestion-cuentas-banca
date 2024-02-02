package com.prueba.gestionbanca.expose.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransferOperationResponseTest {
    @Test
    public void testNoArgsConstructor() {
        TransferOperationResponse response = new TransferOperationResponse();
        assertNotNull(response);
        assertNull(response.getNumberOperation());
        assertNull(response.getTypeAccountFrom());
        assertNull(response.getNumberAccountFrom());
        assertNull(response.getTypeAccountDestiny());
        assertNull(response.getNumberAccountDestiny());
        assertNull(response.getNumberDocumentThird());
        assertNull(response.getNameThird());
        assertNull(response.getAmountTransfer());
    }

    @Test
    public void testAllArgsConstructor() {
        String numberOperation = "12345";
        String typeAccountFrom = "Savings";
        String numberAccountFrom = "987654321";
        String typeAccountDestiny = "Checking";
        String numberAccountDestiny = "123456789";
        String numberDocumentThird = "789456123";
        String nameThird = "John Doe";
        BigDecimal mountTransfer = BigDecimal.valueOf(1000);

        TransferOperationResponse response = new TransferOperationResponse(numberOperation,
                typeAccountFrom, numberAccountFrom, typeAccountDestiny, numberAccountDestiny,
                numberDocumentThird, nameThird, mountTransfer);
        assertNotNull(response);
        assertEquals(numberOperation, response.getNumberOperation());
        assertEquals(typeAccountFrom, response.getTypeAccountFrom());
        assertEquals(numberAccountFrom, response.getNumberAccountFrom());
        assertEquals(typeAccountDestiny, response.getTypeAccountDestiny());
        assertEquals(numberAccountDestiny, response.getNumberAccountDestiny());
        assertEquals(numberDocumentThird, response.getNumberDocumentThird());
        assertEquals(nameThird, response.getNameThird());
        assertEquals(mountTransfer, response.getAmountTransfer());
    }
}
