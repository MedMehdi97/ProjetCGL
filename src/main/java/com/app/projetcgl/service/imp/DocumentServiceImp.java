package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.repository.DocumentPagingRepository;
import com.app.projetcgl.repository.DocumentRepository;
import com.app.projetcgl.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private DocumentPagingRepository documentPagingRepository;


    /**
     * Constructeur initialisant le documentRepository
     * @param documentRepository
     * @param documentPagingRepository
     */
    public DocumentServiceImp(DocumentRepository documentRepository, DocumentPagingRepository documentPagingRepository) {
        this.documentRepository = documentRepository;
        this.documentPagingRepository = documentPagingRepository;
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

    /**
     * Recupration de la liste de document dans une page
     * @param nbPage
     * @param nbElm
     * @return
     */
    @Override
    public Page<Document> getAllDocumentByPage(int nbPage, int nbElm) {
        Pageable page= PageRequest.of(nbPage, nbElm);

        return documentPagingRepository.findAll(page);
    }
}
