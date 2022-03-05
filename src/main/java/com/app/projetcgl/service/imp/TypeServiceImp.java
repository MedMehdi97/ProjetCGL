package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Type;
import com.app.projetcgl.repository.TypeRepository;
import com.app.projetcgl.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImp implements TypeService {
    private TypeRepository typeRepository;

    public TypeServiceImp(TypeRepository typeRepository) {
        super();
        this.typeRepository = typeRepository;
    }

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getTypeById(long id) {
        return typeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Type", "IdType", id));
    }

    @Override
    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    @Override
    public Type updateType(Type type, long id) {
        Type existingType=getTypeById(id);
        existingType.setLibType(type.getLibType());
        return existingType;
    }

    @Override
    public void deleteType(long id) {
        Type type=getTypeById(id);
        typeRepository.delete(type);
    }
}
