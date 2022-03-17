package com.app.projetcgl.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TypeTest {

    @Test
    void should_create_empty_document() {
        Type type = new Type();
        assertThat(type).isNotNull();
    }

    @Test
    void should_create_Type() {
        String libType = "text";

        Type type = new Type(libType);

        assertThat(type).isNotNull();
        assertThat(type.getLibType()).isEqualTo(libType);
    }

    @Test
    void should_test_setters(){
        Type type = new Type();

        String libType = "text";


        type.setLibType(libType);

        assertThat(type).isNotNull();
        assertThat(type.getLibType()).isEqualTo(libType);
    }
    @Test
    void testEquals() {
        Type type1 = new Type("text");
        Type type2 = new Type("audio");
        Type type3 = new Type("text");

        assertThat(type1)
                .isEqualTo(type1)        // Test si les deux objets sont les mêmes
                .isNotEqualTo(type2)     // Test si les deux objets sont strictement différents
                .isNotEqualTo("test")           // Test si les deux objets n'ont pas le même type
                .isEqualTo(type3);        // Test si les deux objets sont égaux sans être le même objet
    }

    @Test
    void testHashCode() {
        Type type1 = new Type("text");
        Type type2 = new Type("text");


        assertThat(type1).hasSameHashCodeAs(type2);
    }

    @Test
    void testToString() {
        String libType = "text";

        Type type = new Type(libType);

        String expected = "Type(idType=0, libType=" + libType + ")";
        assertThat(expected).isEqualTo(type.toString());
    }

}