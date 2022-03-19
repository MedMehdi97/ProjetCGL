package com.app.projetcgl.repository;

import com.app.projetcgl.model.Document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentPagingRepository extends PagingAndSortingRepository <Document, Long> {
    Page<Document> findAllByNomStartingWith(String lib, Pageable page);
}
