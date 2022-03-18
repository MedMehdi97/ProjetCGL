package com.app.projetcgl.repository;

import com.app.projetcgl.model.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DocumentPagingRepository extends PagingAndSortingRepository <Document, Long> {
    List<Document> findAllByNomStartingWith(String lib, Pageable page);
}
