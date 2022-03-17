package com.app.projetcgl.controller;

import com.app.projetcgl.model.Type;
import com.app.projetcgl.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controleur de la classe Type
 */
@CrossOrigin
@RestController
public class TypeController {
    /**
     * Type service
     */
    private TypeService typeService;

    /**
     * Constructeur intiant le controleur
     * @param typeService
     */
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * Liste des types
     * @return
     */
    @GetMapping("type/all")
    public ResponseEntity<List<Type>> getAllType(){
        return new ResponseEntity<List<Type>>(typeService.getAllType(),HttpStatus.OK);
    }
}
