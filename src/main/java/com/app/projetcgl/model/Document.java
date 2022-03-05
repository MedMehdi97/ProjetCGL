package com.app.projetcgl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table (name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocument;

    @Column(name = "lien", nullable = false)
    @NonNull
    private String lien;

    @Column(name = "date_archivage", nullable = false)
    @NonNull
    private Date dateArchivage;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_type", nullable=false)
    @NonNull
    private Type type;

    public Document(long idDocument, @NonNull String lien, @NonNull Date dateArchivage, @NonNull Type type) {
        this.idDocument = idDocument;
        this.lien = lien;
        this.dateArchivage = dateArchivage;
        this.type = type;
    }
}
