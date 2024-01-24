package com.prueba.gestionbanca.util;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EnumOperationTypeTest {
    @Test
    void testEnumValues() {

        assertThat(EnumOperationType.RETIRO).isNotNull();
        assertThat(EnumOperationType.DEPOSITO).isNotNull();
        assertThat(EnumOperationType.CONSUMO).isNotNull();
    }
}
