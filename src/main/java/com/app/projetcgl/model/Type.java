package com.app.projetcgl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Type")
/**
 * Classe Model d'un type document
 */
public class Type {
    /**
     * Id Type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idType;
    /**
     * Libellé Type
     */
    @Column(name = "lib_type", nullable = false)
    @NonNull
    private String libType;

    /**
     * Constructeur initialisant un Type
     * @param libType
     */
    public Type(@NonNull String libType) {
        this.libType = libType;
    }
}
