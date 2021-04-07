package ru.bellintegrator.practice.service.organization;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.organization.OrganizationListFilter;
import ru.bellintegrator.practice.view.organization.OrganizationListItemView;
import ru.bellintegrator.practice.view.organization.OrganizationSaveView;
import ru.bellintegrator.practice.view.organization.OrganizationUpdateView;
import ru.bellintegrator.practice.view.organization.OrganizationView;

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
     * @return
     * @param filter
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationListItemView> findAllByFilter(OrganizationListFilter filter) {
        List<Organization> all = dao.findAllByFilter(filter);
        return mapperFacade.mapAsList(all, OrganizationListItemView.class);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView findById(Long id) throws DataNotFoundException {
        Optional<Organization> result = dao.findById(id);
        if (result.isPresent()) {
            return mapperFacade.map(result.get(), OrganizationView.class);
        } else {
            throw new DataNotFoundException("No organization with this id was found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(OrganizationSaveView view) {
        Organization organization = mapperFacade.map(view, Organization.class);
        dao.insert(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(OrganizationUpdateView organization) throws UpdateException {
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
        } else {
            throw new UpdateException("No organization with this id was found");
        }
    }
}
