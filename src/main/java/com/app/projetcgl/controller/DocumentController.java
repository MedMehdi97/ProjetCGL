package com.app.projetcgl.controller;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
public class DocumentController {
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping()
    public String hello(){
        return "Hello World !";
    }

    @PostMapping()
    public ResponseEntity<Document> saveDocument(@RequestBody Document document){
        return new ResponseEntity<Document>(documentService.saveDocument(document), HttpStatus.CREATED);
    }
}
