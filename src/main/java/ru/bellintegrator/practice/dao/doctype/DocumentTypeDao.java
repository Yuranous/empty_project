package ru.bellintegrator.practice.dao.doctype;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.practice.model.DocumentType;

/**
 * DAO для работы с DocumentType
 */
public interface DocumentTypeDao extends JpaRepository<DocumentType, Long> {
    Optional<DocumentType> findByName(String name);
}
