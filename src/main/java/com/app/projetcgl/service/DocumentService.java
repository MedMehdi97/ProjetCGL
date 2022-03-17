package com.app.projetcgl.service;

import com.app.projetcgl.model.Document;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.HashMap;
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
    HashMap<LocalDate,Integer> statistiqueArchivage();
    Page<Document> getAllDocumentByPage(int nbPage, int nbElm);
}
