package ru.bellintegrator.practice.controller.office;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.office.OfficeListItemView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.service = officeService;
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid OfficeSaveView office) throws SaveException {
        service.save(office);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid OfficeUpdateView office) throws UpdateException {
        service.update(office);
    }

    @GetMapping("/list")
    public List<OfficeListItemView> offices(
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

        return service.finAllBySearchCriteria(params);
    }

    @GetMapping("/{id}")
    public OfficeView office(@PathVariable Long id) throws DataNotFoundException {
        return service.findById(id);
    }
}

