package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.*;
import com.app.projetcgl.repository.DocumentPagingRepository;
import com.app.projetcgl.repository.DocumentRepository;
import com.app.projetcgl.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public List<StatDate> statistiqueArchivageBydate() {
        List<StatDate> statistique=new ArrayList<>();
        List<LocalDate> listDate=documentRepository.findDistinctDateArchivage();
        for (int i=0; i<listDate.size();i++){
            statistique.add(new StatDate(listDate.get(i),documentRepository.countDocumentsByDateArchivage(listDate.get(i))));
        }
        return statistique;
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

    /**
     * Statisques Archivage par type
     * @return
     */
    @Override
    public List<StatType> statistiqueArchivageByType() {
        List<StatType> statistique=new ArrayList<>();
        List<Type> listType=documentRepository.findDistinctType();
        for (int i=0; i<listType.size();i++){
            statistique.add(new StatType(listType.get(i).getLibType(),documentRepository.countDocumentsByType(listType.get(i))));
        }
        return statistique;
    }

    /**
     * Statistique d'archivage par date et type
     * @return
     */
    @Override
    public List<StatDateType> statistiqueArchivageByDateAndType() {
        List<StatDateType> resultStat=new ArrayList<>();
        List<DateType> listDateType=getDistinctDateType();
        for (int i=0; i<listDateType.size();i++){
                Type type=listDateType.get(i).getType();
                LocalDate date=listDateType.get(i).getDateArchivage();
                resultStat.add(new StatDateType(date,type.getLibType(),documentRepository.countDocumentsByDateArchivageAndType(date,type)));
        }
        return resultStat;
    }

    /**
     * Recherche d'un document par nom
     * @param lib
     * @return
     */
    @Override
    public List<Document> getDocumentsStartingWith(String lib) {
        return documentRepository.findDocumentByNomStartingWith(lib);
    }

    /**
     * Construction de la liste DateType distinct
     * @return
     */
    public List<DateType> getDistinctDateType() {
        List<Object[]> list=documentRepository.findDistinctDateAndType();
        List<DateType> result=new ArrayList<>();
        for (Object [] object : list){
             result.add(new DateType((LocalDate) object[0], (Type) object[1]));
        }
        return result;
    }



}
