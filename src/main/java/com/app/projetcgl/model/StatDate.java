package com.app.projetcgl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Classe Pour la représentation des statistiques par date
 */
public class StatDate {
    LocalDate dateArchivage;
    int value;
}
