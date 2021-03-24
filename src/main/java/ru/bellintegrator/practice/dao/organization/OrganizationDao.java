package ru.bellintegrator.practice.dao.organization;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Organization;

public interface OrganizationDao {
    List<Organization> findAll(List<SearchCriteria> params);

    Optional<Organization> findById(Long id);

    void insert(Organization organization);

    void update(Organization organization);
}
