package com.app.projetcgl.repository;

import com.app.projetcgl.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
