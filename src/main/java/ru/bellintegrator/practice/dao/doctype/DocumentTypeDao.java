package ru.bellintegrator.practice.dao.doctype;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.practice.model.DocumentType;

/**
 * DAO типа документа
 */
public interface DocumentTypeDao extends JpaRepository<DocumentType, Long> {

    /**
     * Получить тип документа по наименованию
     *
     * @param name наименование документа
     *
     * @return Тип документа
     */
    Optional<DocumentType> findByName(String name);
}
