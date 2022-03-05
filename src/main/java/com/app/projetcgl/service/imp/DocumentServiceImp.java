package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.repository.DocumentRepository;
import com.app.projetcgl.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImp implements DocumentService {
    private DocumentRepository documentRepository;

    public DocumentServiceImp(DocumentRepository documentRepository) {
        super();
        this.documentRepository = documentRepository;
    }

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document getDocumentById(long id) {
        return documentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Document", "IdDocument", id));
    }

    @Override
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    @Override
    public Document updateDocument(Document document, long id) {
        Document existingDocument=getDocumentById(id);
        existingDocument.setLien(document.getLien());
        existingDocument.setDateArchivage(document.getDateArchivage());
        existingDocument.setType(document.getType());

        return existingDocument;
    }

    @Override
    public void deleteDocument(long id) {
        Document document=getDocumentById(id);
        documentRepository.delete(document);
    }
}
