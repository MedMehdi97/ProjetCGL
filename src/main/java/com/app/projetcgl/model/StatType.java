package com.app.projetcgl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * Classe Pour la représentation des statistiques par type
 */
public class StatType {
    String type;
    int value;
}
