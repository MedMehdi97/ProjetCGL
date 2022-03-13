package com.app.projetcgl.repository;

import com.app.projetcgl.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository de Type
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    /**
     * Rechercher un type à partir du libellé
     * @param libType
     * @return
     */
    Optional<Type> findByLibType(String libType);
}
