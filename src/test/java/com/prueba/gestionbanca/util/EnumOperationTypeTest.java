package com.prueba.gestionbanca.util;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnumOperationTypeTest {
    @Test
    public void testGetByCodeValid() {
        int code = 1;
        EnumOperationType result = EnumOperationType.getByCode(code);
        assertEquals(EnumOperationType.PAGO_MINIMO, result);
    }

    @Test
    public void testGetByCodeInvalid() {
        int code = 10;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> EnumOperationType.getByCode(code));
        assertEquals("No EnumOperationType found with code: " + code, exception.getMessage());
    }

    @Test
    public void testGetByNombreValid() {
        String nombre = "PAGO_TOTAL";
        EnumOperationType result = EnumOperationType.getByNombre(nombre);
        assertEquals(EnumOperationType.PAGO_TOTAL, result);
    }

    @Test
    public void testGetByNombreInvalid() {
        String nombre = "INVALID";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> EnumOperationType.getByNombre(nombre));
        assertEquals("No EnumOperationType found with code: " + nombre, exception.getMessage());
    }

}
