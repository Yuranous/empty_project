package ru.bellintegrator.practice.controller.office;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.wrapper.ResponseWrapper;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.OfficeView;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.service = officeService;
    }

    @PostMapping("/save")
    public ResponseWrapper addOffice(@RequestBody OfficeView office) {
        if (office.getOrgId() == null) {
            return ResponseWrapper.getErrorResponse("400", "OrgId value must be passed");
        } else {
            service.add(office);
            return ResponseWrapper.getSuccessResponse("OK");
        }
    }

    @PutMapping("/update")
    public ResponseWrapper updateOffice(@RequestBody OfficeView office) {
        if (office.getId() == null || office.getName() == null || office.getAddress() == null) {
            return ResponseWrapper.getErrorResponse("400", "id, name, address values must be pass");
        } else {
            if (service.update(office)) {
                return ResponseWrapper.getSuccessResponse("OK");
            } else {
                return ResponseWrapper.getErrorResponse("400", "Error during updating");
            }
        }
    }

    @GetMapping("/list")
    public ResponseWrapper offices(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long orgId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Boolean isActive
            ) {

        List<SearchCriteria> params = new ArrayList<SearchCriteria>();

        if (name != null) {
            params.add(new SearchCriteria("name", ":", name));
        }

        if (orgId != null) {
            params.add(new SearchCriteria("orgId", ":", orgId));
        }

        if (address != null) {
            params.add(new SearchCriteria("address", ":", address));
        }

        if (phone != null) {
            params.add(new SearchCriteria("phone", ":", phone));
        }

        if (isActive != null) {
            params.add(new SearchCriteria("isActive", ":", isActive));
        }

        List<OfficeView> offices = service.offices(params);

        if (offices.isEmpty()) {
            return ResponseWrapper.getErrorResponse("404", "No offices found");
        } else {
            return ResponseWrapper.getSuccessResponse(offices);
        }
    }

    @GetMapping("/{id}")
    public ResponseWrapper office(@PathVariable Long id) {
        Optional<OfficeView> result = service.office(id);
        if (result.isPresent()) {
            return ResponseWrapper.getSuccessResponse(result.get());
        } else {
            return ResponseWrapper.getErrorResponse("404", "Can't find office with passed id value");
        }
    }
}

