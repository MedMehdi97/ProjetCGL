package com.app.projetcgl.service;

import com.app.projetcgl.model.Type;

import java.util.List;


public interface TypeService {
    Type saveType(Type type);
    Type getTypeById(long id);
    List<Type> getAllType();
    Type updateType(Type type, long id);
    void deleteType(long id);

}
