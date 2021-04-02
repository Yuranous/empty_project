package ru.bellintegrator.practice.service.doctype;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.doctype.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.doctype.DocumentTypeView;

/**
 * {@inheritDoc}
 */
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentTypeView> getAllDocTypes() {
        List<DocumentType> all = dao.findAll();
        return mapperFacade.mapAsList(all, DocumentTypeView.class);
    }
}
