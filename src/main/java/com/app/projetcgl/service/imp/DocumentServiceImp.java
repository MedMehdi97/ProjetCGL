package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.repository.DocumentRepository;
import com.app.projetcgl.service.DocumentService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe DocumentServiceImp
 */
@Service
public class DocumentServiceImp implements DocumentService {
    /**
     * Document Repository
     */
    private DocumentRepository documentRepository;

    /**
     * Constructeur initialisant le documentRepository
     * @param documentRepository
     */
    public DocumentServiceImp(DocumentRepository documentRepository) {
        super();
        this.documentRepository = documentRepository;
    }

    /**
     * Enregistrement d'un document
     * @param document
     * @return
     */
    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    /**
     * Recherche d'un document avec son Id
     * @param id
     * @return
     */
    @Override
    public Document getDocumentById(long id) {
        return documentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Document", "IdDocument", id));
    }

    /**
     * Liste de tous les documents
     * @return
     */
    @Override
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    /**
     * Modifier un document
     * @param document
     * @param id
     * @return
     */
    @Override
    public Document updateDocument(Document document, long id) {
        Document existingDocument=getDocumentById(id);
        existingDocument.setLien(document.getLien());
        existingDocument.setDateArchivage(document.getDateArchivage());
        existingDocument.setType(document.getType());

        return existingDocument;
    }

    /**
     * Suppression d'un document
     * @param id
     */
    @Override
    public void deleteDocument(long id) {
        Document document=getDocumentById(id);
        documentRepository.delete(document);
    }

    /**
     * Statistiques d'archivage
     * @return
     */
    @Override
    public HashMap<LocalDate, Integer> statistiqueArchivage() {
        Map<LocalDate,Integer> statistique=new HashMap<>();
        List<LocalDate> listDate=documentRepository.findDistinctDateArchivage();
        for (int i=0; i<listDate.size();i++){
            statistique.put(listDate.get(i),documentRepository.countDocumentsByDateArchivage(listDate.get(i)));
        }
        return (HashMap<LocalDate, Integer>) statistique;
    }
}
