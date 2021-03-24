package ru.bellintegrator.practice.controller.organization;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bellintegrator.practice.view.wrapper.ResponseWrapper.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;
import ru.bellintegrator.practice.view.wrapper.ResponseWrapper;

@Api(value = "OrganizationController")
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.service = organizationService;
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public ResponseWrapper addOrganization(@RequestBody OrganizationView organization) {
        if (organization.getName() == null || organization.getFullName() == null ||organization.getInn() == null ||
                organization.getKpp() == null || organization.getAddress() == null) {
            return getErrorResponse("400", "name, fullName, inn, kpp, address " +
                    "values must be passed");
        } else {
            if (service.add(organization)) {
                return getSuccessResponse("OK");
            } else {
                return getErrorResponse("400", "Error during saving");
            }
        }
    }

    @ApiOperation(value = "Обновить организацию", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PutMapping("/update")
    public ResponseWrapper updateOrganization(@RequestBody OrganizationView organization) {
        if (organization.getId() == null || organization.getName() == null || organization.getFullName() == null ||organization.getInn() == null ||
                organization.getKpp() == null || organization.getAddress() == null) {
            return getErrorResponse("400", "name, fullName, inn, kpp, address " +
                    "values must be passed");
        } else {
            if (service.update(organization)) {
                return getSuccessResponse("OK");
            } else {
                return getErrorResponse("400", "Error during updating");
            }
        }
    }

    @ApiOperation(value = "Получить список всех организаций", httpMethod = "GET")
    @GetMapping("/list")
    public ResponseWrapper organizations(
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

        List<OrganizationView> organizations = service.organizations(params);

        if (organizations.isEmpty()) {
            return getErrorResponse("404", "No organizations found");
        } else {
            return getSuccessResponse(organizations);
        }
    }

    @ApiOperation(value = "Получить организацию по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseWrapper organization(@PathVariable Long id) {
        Optional<OrganizationView> result = service.organization(id);
        if (result.isPresent()) {
            return getSuccessResponse(result.get());
        } else {
            return getErrorResponse("404", "Can't find organization with passed id value");
        }
    }
}

