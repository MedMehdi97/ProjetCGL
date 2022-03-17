package com.app.projetcgl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Model Date et Type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateType {
    /**
     * date Archivage
     */
    private LocalDate dateArchivage;
    /**
     * Type de données
     */
    private Type type;
}
