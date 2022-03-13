package com.app.projetcgl.controller;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.service.DocumentService;
import com.app.projetcgl.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Controller Document
 */
@RestController
public class DocumentController {
    /**
     * DocumentService
     */
    private DocumentService documentService;
    /**
     * TypeService
     */
    private TypeService typeService;

    /**
     * Constructeur initialisant les paramètres du controller
     * @param documentService
     * @param typeService
     */
    public DocumentController(DocumentService documentService, TypeService typeService) {
        this.documentService = documentService;
        this.typeService= typeService;
    }

    /**
     * Liste des documents
     * @return
     */
    @GetMapping("all")
    public ResponseEntity<List<Document>> getAllDocuments(){
        return new ResponseEntity<List<Document>>(documentService.getAllDocument(),HttpStatus.OK);
    }

    /**
     * Ajout d'un document
     * @param lien
     * @param dateArchivage
     * @param typeLib
     * @return
     */
    @PostMapping("Document")
    public ResponseEntity<Document> saveDocument(@RequestParam("lien") String lien, @RequestParam("dateArchivage") String dateArchivage, @RequestParam("typeDocument") String typeLib){
        return new ResponseEntity<Document>(documentService.saveDocument(new Document(lien,LocalDate.parse(dateArchivage),typeService.getTypeByLib(typeLib))), HttpStatus.CREATED);
    }

    /**
     * Suppriression d'un document
     * @param idDocument
     * @return
     */
    @DeleteMapping("Delete/{idDocument}")
    public ResponseEntity<String> deleteDocument(@PathVariable("idDocument") long idDocument){
        documentService.deleteDocument(idDocument);
        return new ResponseEntity<>("Document deleted successfully !",HttpStatus.OK);
    }

    /**
     * Nombre d'archivage par date
     * @return
     */
    @GetMapping("Statistiques")
    public ResponseEntity<HashMap<LocalDate,Integer>> getStatistiques(){
        return new ResponseEntity<HashMap<LocalDate,Integer>>(documentService.statistiqueArchivage(),HttpStatus.OK);
    }

}
