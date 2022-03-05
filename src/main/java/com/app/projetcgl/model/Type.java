package com.app.projetcgl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idType;

    @Column(name = "lib_type", nullable = false)
    @NonNull
    private String libType;

    public Type(@NonNull String libType) {
        this.libType = libType;
    }
}
