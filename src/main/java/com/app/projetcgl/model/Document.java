package com.app.projetcgl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table (name = "Document")
/**
 * Classe Model d'un Document
 */
public class Document {
    /**
     * Id d'un document
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocument;
    /**
     * Lien du document
     */
    @Column(name = "lien", nullable = false)
    @NonNull
    private String lien;
    /**
     * Date Archivage
     */
    @Column(name = "date_archivage", nullable = false)
    @NonNull
    private LocalDate dateArchivage;

    /**
     * Type du document
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="id_type", nullable=false)
    @NonNull
    private Type type;

    /**
     * Constructeur initialisant un Document
     * @param lien
     * @param dateArchivage
     * @param type
     */
    public Document(@NonNull String lien, @NonNull LocalDate dateArchivage, @NonNull Type type) {
        this.idDocument = idDocument;
        this.lien = lien;
        this.dateArchivage = dateArchivage;
        this.type = type;
    }
}
