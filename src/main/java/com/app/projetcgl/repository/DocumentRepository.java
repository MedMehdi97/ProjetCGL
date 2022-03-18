package com.app.projetcgl.repository;

import com.app.projetcgl.model.DateType;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.SqlResultSetMapping;
import javax.xml.crypto.Data;
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

    /**
     * Recherche des types distinct
     * @return
     */
    @Query("SELECT DISTINCT type FROM Document")
    List<Type> findDistinctType();

    /**
     * Nombre de documents d'un type
     * @param type
     * @return
     */
    int countDocumentsByType(Type type);

    /**
     * Recherche des dateArchivage et type différent
     * @return
     */
    @Query("SELECT DISTINCT dateArchivage, type from Document ")
    List<Object[]> findDistinctDateAndType();

    /**
     * Nombre de document par date et type
     * @param dateArchivage
     * @param type
     * @return
     */
    int countDocumentsByDateArchivageAndType(LocalDate dateArchivage, Type type);

    /**
     * Recherche document par nom
     * @param starting
     * @return
     */
    List<Document> findDocumentByNomStartingWith(String starting);
}
