package ru.bellintegrator.practice.service.doctype;

import java.util.List;
import ru.bellintegrator.practice.view.DocumentTypeView;

/**
 * Сервис типов документов
 */
public interface DocumentTypeService {

    /**
     * Получить список типов документов из справочника
     *
     * @return Список информации о типах документов
     */
    List<DocumentTypeView> docTypes();
}
