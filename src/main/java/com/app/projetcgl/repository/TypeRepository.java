package com.app.projetcgl.repository;

import com.app.projetcgl.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TypeRepository extends JpaRepository<Type,Long> {
    Optional<Type> findByLibType(String libType);
}
