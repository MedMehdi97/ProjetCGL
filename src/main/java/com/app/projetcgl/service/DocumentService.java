package com.app.projetcgl.service;

import com.app.projetcgl.model.Document;

import java.util.List;

public interface DocumentService {
    Document saveDocument(Document document);
    Document getDocumentById(long id);
    List<Document> getAllDocument();
    Document updateDocument(Document document, long id);
    void deleteDocument(long id);
}
