package ru.bellintegrator.practice.service.office;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.OfficeView;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;


    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> offices(List<SearchCriteria> params) {
        List<Office> all = officeDao.findAll(params);
        return all.stream().map(office -> {
            Long orgId = office.getOrganization().getId();
            OfficeView res = mapperFacade.map(office, OfficeView.class);
            res.setOrgId(orgId);
            return res;
        }).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OfficeView> office(Long id) {
        Optional<Office> result = officeDao.findById(id);
        if (result.isPresent()) {
            Office office = result.get();
            Long orgId = office.getOrganization().getId();
            OfficeView view = mapperFacade.map(office, OfficeView.class);
            view.setOrgId(orgId);
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
    public boolean add(OfficeView view) {
        Office office = mapperFacade.map(view, Office.class);
        Optional<Organization> organization = organizationDao.findById(view.getOrgId());
        if (organization.isPresent()) {
            office.setOrganization(organization.get());
            officeDao.insert(office);
            return true;
        } else {
            return false;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean update(OfficeView office) {
        Optional<Office> source = officeDao.findById(office.getId());

        if (source.isPresent()) {
            Office src = source.get();
            src.setName(office.getName());
            src.setAddress(office.getAddress());
            src.setPhone(office.getPhone());
            if (office.getIsActive() != null) {
                src.setIsActive(office.getIsActive());
            }

            officeDao.update(src);

            return true;
        }
        return false;
    }
}
