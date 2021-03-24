package ru.bellintegrator.practice.controller.doctype;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.doctype.DocumentTypeService;
import ru.bellintegrator.practice.view.DocumentTypeView;

@Api(value = "DocumentTypeController")
@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocumentTypeController {

    private final DocumentTypeService service;

    @Autowired
    public DocumentTypeController(DocumentTypeService service) {
        this.service = service;
    }

    @ApiOperation(value = "Посмотреть список документов в справочнике", httpMethod = "GET")
    @GetMapping
    public Map<String, List<DocumentTypeView>> countries() {
        Map<String, List<DocumentTypeView>> result = new HashMap<>();
        List<DocumentTypeView> docTypes = service.docTypes();
        if (!docTypes.isEmpty()) {
            result.put("data", docTypes);
        }
        return result;
    }
}
