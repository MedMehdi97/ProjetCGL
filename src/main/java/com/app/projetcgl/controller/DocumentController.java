package com.app.projetcgl.controller;
import com.app.projetcgl.model.*;
import com.app.projetcgl.service.DocumentService;
import com.app.projetcgl.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Controller Document
 */
@CrossOrigin
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
     * Liste des documents par page
     * @param page
     * @param nbElm
     * @return
     */
    @GetMapping("all/page")
    public ResponseEntity<Page<Document>>  getDocumentsByPage(@RequestParam("numPage") int page, @RequestParam("nbElm") int nbElm){
        return new ResponseEntity<Page<Document>>(documentService.getAllDocumentByPage(page, nbElm),HttpStatus.OK);
    }

    /**
     * Ajout d'un document
     * @param lien
     * @param typeLib
     * @return
     */
    @PostMapping("Document")
    public ResponseEntity<Document> saveDocument(@RequestParam("nomDocument") String nomDocument, @RequestParam("lien") String lien , @RequestParam("typeDocument") String typeLib){
        return new ResponseEntity<Document>(documentService.saveDocument(new Document(nomDocument, lien,typeService.getTypeByLib(typeLib))), HttpStatus.CREATED);
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
    @GetMapping("Statistiques/date")
    public ResponseEntity<List<StatDate>> getStatistiquesByDate(){
        return new ResponseEntity<List<StatDate>>(documentService.statistiqueArchivageBydate(),HttpStatus.OK);
    }

    /**
     * Nombre d'archivage par type
     * @return
     */
    @GetMapping("Statistiques/type")
    public ResponseEntity<List<StatType>> getStatistiquesByType(){
        return new ResponseEntity<List<StatType>>(documentService.statistiqueArchivageByType(),HttpStatus.OK);
    }

    /**
     * Nombre d'archivage par date et type
     * @return
     */
    @GetMapping("Statistiques/all")
    public ResponseEntity<List<StatDateType>> getStatistiquesByDateAndType(){
        return new ResponseEntity<List<StatDateType>>(documentService.statistiqueArchivageByDateAndType(),HttpStatus.OK);
    }

    /**
     * Recherche document par nom
     * @param nom
     * @return
     */
    @GetMapping("Document/Recherche")
    public ResponseEntity<List<Document>> getDocumentsStartingWith(@RequestParam("nom") String nom){
        return new ResponseEntity<List<Document>>(documentService.getDocumentsStartingWith(nom),HttpStatus.OK);
    }

    @GetMapping("Document/Recherche/Page")
    public ResponseEntity<Page<Document>> getDocumentsStartingWithByPage(@RequestParam("nom") String nom, @RequestParam("numPage") int numPage, @RequestParam("nbElm") int nbElm){
        return new ResponseEntity<Page<Document>>(documentService.getDocumentsStartingWithByPage(nom,numPage,nbElm),HttpStatus.OK);
    }


}
