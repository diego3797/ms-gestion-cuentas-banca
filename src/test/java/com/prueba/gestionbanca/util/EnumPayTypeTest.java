package com.prueba.gestionbanca.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumPayTypeTest {
    @Test
    void testEnumValues() {

        assertThat(EnumPayType.ABONO ).isNotNull();
        assertThat(EnumPayType.PAGO_MINIMO).isNotNull();
        assertThat(EnumPayType.PAGO_TOTAL).isNotNull();
    }
}
