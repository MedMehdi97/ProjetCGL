package com.app.projetcgl.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DocumentTest {

    @Test
    void should_create_empty_document() {
        Document doc = new Document();
        assertThat(doc).isNotNull();
    }

    @Test
    void should_create_document() {
        String nom = "Test";
        String lien = "LienTest";
        Type type = new Type("text");
        LocalDate date = LocalDate.now();

        Document doc = new Document(nom, lien, type);

        assertThat(doc).isNotNull();
        assertThat(doc.getNom()).isEqualTo(nom);
        assertThat(doc.getLien()).isEqualTo(lien);
        assertThat(doc.getType().getLibType()).isEqualTo(type.getLibType());
        assertThat(doc.getDateArchivage()).isEqualTo(date);
    }

    @Test
    void should_test_setters(){
        Document doc = new Document();
        String nom = "Test";
        String lien = "LienTest";
        Type type = new Type("text");
        LocalDate date = LocalDate.now();

        doc.setNom(nom);
        doc.setLien(lien);
        doc.setType(type);
        doc.setDateArchivage(date);

        assertThat(doc).isNotNull();
        assertThat(doc.getNom()).isEqualTo(nom);
        assertThat(doc.getLien()).isEqualTo(lien);
        assertThat(doc.getType()).isEqualTo(type);
        assertThat(doc.getDateArchivage()).isEqualTo(date);
    }

    @Test
    void testEquals() {
        Document doc1 = new Document("Test1", "Lien1", new Type("text"));
        Document doc2 = new Document("Test2", "Lien2", new Type("text"));
        Document doc3 = new Document("Test1", "Lien1", new Type("text"));
        Document doc4 = new Document("Test1", "Lien3", new Type("text"));

        assertThat(doc1)
                .isEqualTo(doc1)        // Test si les deux objets sont les mêmes
                .isNotEqualTo(doc2)     // Test si les deux objets sont strictement différents
                .isNotEqualTo("test")           // Test si les deux objets n'ont pas le même type
                .isEqualTo(doc3)        // Test si les deux objets sont égaux sans être le même objet                .isNotEqualTo("test")           // Test si les deux objets n'ont pas le même type
                .isNotEqualTo(doc4);    // Test si les deux n'ont pas la même date
    }

    @Test
    void testHashCode() {
        Document doc1 = new Document("Test1", "Lien1", new Type("text"));
        Document doc2 = new Document("Test1", "Lien1", new Type("text"));


        assertThat(doc1).hasSameHashCodeAs(doc2);
    }

    @Test
    void testToString() {
        String nom = "Test";
        String lien = "LienTest";
        Type type = new Type("text");
        LocalDate date = LocalDate.now();

        Document doc = new Document(nom, lien, type);

        String expected = "Document(idDocument=0, nom=" + nom + ", lien=" + lien + ", dateArchivage=" + date + ", type=" + type.toString() + ")";
        assertThat(expected).isEqualTo(doc.toString());
    }

}