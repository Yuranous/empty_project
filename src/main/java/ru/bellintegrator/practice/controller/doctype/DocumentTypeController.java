package ru.bellintegrator.practice.controller.doctype;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.doctype.DocumentTypeService;
import ru.bellintegrator.practice.view.doctype.DocumentTypeView;

@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocumentTypeController {

    private final DocumentTypeService service;

    @Autowired
    public DocumentTypeController(DocumentTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<DocumentTypeView> docTypes() {
        return service.getAllDocTypes();
    }
}
