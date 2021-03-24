package ru.bellintegrator.practice.service.doctype;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.DocumentTypeView;

/**
 * Сервис справочника документов
 */
@Validated
public interface DocumentTypeService {

    /**
     * Получить список документов из справочника
     *
     * @return {@DocumentType}
     */
    List<DocumentTypeView> docTypes();
}
