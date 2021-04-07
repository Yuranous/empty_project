package ru.bellintegrator.practice.service.office;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.office.OfficeListFilter;
import ru.bellintegrator.practice.view.office.OfficeListItemView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

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
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListItemView> finAllByFilter(OfficeListFilter filter) {
        List<Office> all = officeDao.finAllByFilter(filter);
        return mapperFacade.mapAsList(all, OfficeListItemView.class);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView findById(Long id) throws DataNotFoundException {
        Optional<Office> result = officeDao.findById(id);
        if (result.isPresent()) {
            Office office = result.get();
            return mapperFacade.map(office, OfficeView.class);
        } else {
            throw new DataNotFoundException("No office with this id was found");
        }
    }

    /**
     * {@inheritDoc}
     * @param view
     */
    @Transactional
    @Override
    public void save(OfficeSaveView view) throws SaveException {
        Office office = mapperFacade.map(view, Office.class);
        Optional<Organization> organization = organizationDao.findById(view.getOrgId());
        if (organization.isPresent()) {
            office.setOrganization(organization.get());
            officeDao.insert(office);
        } else {
            throw new SaveException("There is no organization with passed id. Check OrgId value");
        }
    }

    /**
     * {@inheritDoc}
     * @param office
     */
    @Transactional
    @Override
    public void update(OfficeUpdateView office) throws UpdateException {
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
        } else {
            throw new UpdateException("There is no office with passed id. Check id value");
        }
    }
}
