package com.app.projetcgl.repository;

import com.app.projetcgl.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository du Document
 */
public interface DocumentRepository extends JpaRepository<Document,Long> {
    /**
     * Recherche des Date d'archivage distinct
     * @return
     */
    @Query("SELECT DISTINCT dateArchivage FROM Document ")
    List<LocalDate> findDistinctDateArchivage();

    /**
     * Nombre des documents archivée à une Date
     * @param localDate
     * @return
     */
    int countDocumentsByDateArchivage(LocalDate localDate);
}
