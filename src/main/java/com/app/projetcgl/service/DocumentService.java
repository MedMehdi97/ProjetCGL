package com.app.projetcgl.service;

import com.app.projetcgl.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Interface DocumentService
 */
public interface DocumentService {
    Document saveDocument(Document document);
    Document getDocumentById(long id);
    List<Document> getAllDocument();
    Document updateDocument(Document document, long id);
    void deleteDocument(long id);
    List<StatDate> statistiqueArchivageBydate();
    Page<Document> getAllDocumentByPage(int nbPage, int nbElm);
    List<StatType> statistiqueArchivageByType();
    List<StatDateType> statistiqueArchivageByDateAndType();
    List<Document> getDocumentsStartingWith(String lib);
}
