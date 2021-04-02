package ru.bellintegrator.practice.controller.organization;

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
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.view.organization.OrganizationListItemView;
import ru.bellintegrator.practice.view.organization.OrganizationSaveView;
import ru.bellintegrator.practice.view.organization.OrganizationUpdateView;
import ru.bellintegrator.practice.view.organization.OrganizationView;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.service = organizationService;
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid OrganizationSaveView organization) throws SaveException {
        service.save(organization);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid OrganizationUpdateView organization) throws UpdateException {
        service.update(organization);
    }

    @GetMapping("/list")
    public List<OrganizationListItemView> organizations(
            @RequestParam String name,
            @RequestParam(required = false) String inn,
            @RequestParam(required = false) Boolean isActive
    ) {

        List<SearchCriteria> params = new ArrayList<SearchCriteria>();

        params.add(new SearchCriteria("name", ":", name));

        if (inn != null) {
            params.add(new SearchCriteria("inn", ":", inn));
        }

        if (isActive != null) {
            params.add(new SearchCriteria("isActive", ":", isActive));
        }

        return service.findAllBySearchCriteria(params);
    }

    @GetMapping("/{id}")
    public OrganizationView organization(@PathVariable Long id) throws DataNotFoundException {
        return service.findById(id);
    }
}

