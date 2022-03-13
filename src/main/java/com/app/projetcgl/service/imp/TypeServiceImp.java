package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Type;
import com.app.projetcgl.repository.TypeRepository;
import com.app.projetcgl.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe TypeServiceImp
 */
@Service
public class TypeServiceImp implements TypeService {
    /**
     * Type Repository
     */
    private TypeRepository typeRepository;

    /**
     * Constructeur initialisant la classe
     * @param typeRepository
     */
    public TypeServiceImp(TypeRepository typeRepository) {
        super();
        this.typeRepository = typeRepository;
    }

    /**
     * Enregistement d'un Type
     * @param type
     * @return
     */
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * Recherche d'un Type avec l'id
     * @param id
     * @return
     */
    @Override
    public Type getTypeById(long id) {
        return typeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Type", "IdType", id));
    }

    /**
     * Liste des Type
     * @return
     */
    @Override
    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    /**
     * Recherche d'un Type avec le libellé
     * @param lib
     * @return
     */
    @Override
    public Type getTypeByLib(String lib) {
        return typeRepository.findByLibType(lib).orElseThrow(() ->
                new ResourceNotFoundException("Type", "LibType", lib));
    }

    /**
     * Modification d'un Type
     * @param type
     * @param id
     * @return
     */
    @Override
    public Type updateType(Type type, long id) {
        Type existingType=getTypeById(id);
        existingType.setLibType(type.getLibType());
        return existingType;
    }

    /**
     * Suppression d'un Type
     * @param id
     */
    @Override
    public void deleteType(long id) {
        Type type=getTypeById(id);
        typeRepository.delete(type);
    }
}
