package ru.bellintegrator.practice.service.organization;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.OrganizationView;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;


    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organizations(List<SearchCriteria> params) {
        List<Organization> all = dao.findAll(params);
        return mapperFacade.mapAsList(all, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationView> organization(Long id) {
        Optional<Organization> result = dao.findById(id);
        if (result.isPresent()) {
            OrganizationView view = mapperFacade.map(result.get(), OrganizationView.class);
            return Optional.of(view);
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean add(OrganizationView view) {
        Organization organization = mapperFacade.map(view, Organization.class);
        dao.insert(organization);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean update(OrganizationView organization) {
        Optional<Organization> source = dao.findById(organization.getId());

        if (source.isPresent()) {
            Organization src = source.get();
            src.setName(organization.getName());
            src.setFullName(organization.getFullName());
            src.setInn(organization.getInn());
            src.setKpp(organization.getKpp());
            src.setAddress(organization.getAddress());
            if (organization.getPhone() != null) {
                src.setPhone(organization.getPhone());
            }
            if (organization.getIsActive() != null) {
                src.setIsActive(organization.getIsActive());
            }

            dao.update(src);

            return true;
        }
        return false;
    }
}
