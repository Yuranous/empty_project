package ru.bellintegrator.practice.service.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.doctype.DocumentTypeDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.user.UserListFilter;
import ru.bellintegrator.practice.view.user.UserListItemView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocumentTypeDao documentTypeDao;
    private final MapperFacade mapperFacade;


    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao,
                           CountryDao countryDao, DocumentTypeDao documentTypeDao,
                           MapperFacade mapperFacade) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.documentTypeDao = documentTypeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     * @return
     * @param filter
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserListItemView> findAllUsersByFilter(UserListFilter filter) {
        List<User> all = userDao.findAllByFilter(filter);
        return mapperFacade.mapAsList(all, UserListItemView.class);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public UserView findById(Long id) throws DataNotFoundException {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            User user = result.get();
            UserView view = mapperFacade.map(user, UserView.class);
            Document doc = user.getDocument();
            view.setDocName(doc.getType().getName());
            if (doc.getDate() != null) {
                view.setDocDate(doc.getDate().toString());
            }
            if (doc.getNumber() != null) {
                view.setDocNumber(doc.getNumber());
            }

            Country country = user.getCountry();
            if (country != null) {
                view.setCitizenshipCode(country.getCitizenshipCode());
                view.setCitizenshipName(country.getName());
            }

            return view;
        } else {
            throw new DataNotFoundException("No user with this id was found");
        }
    }

    /**
     * {@inheritDoc}
     * @param view
     */
    @Transactional
    @Override
    public void save(UserSaveView view) throws SaveException {
        User user = mapperFacade.map(view, User.class);
        Optional<Office> office = officeDao.findById(view.getOfficeId());
        if (office.isPresent()) {
            user.setOffice(office.get());
            if (view.getDocCode() != null && view.getDocName() != null && view.getDocNumber() != null && view.getDocDate() != null) {
                Document document = new Document();
                document.setId(user.getId());
                Optional<DocumentType> typeOptional = documentTypeDao.findById(view.getDocCode());

                document.setType(typeOptional.orElseThrow(() ->
                        new SaveException("No documentType with this code was found")
                ));

                document.setNumber(view.getDocNumber());
                document.setDate(LocalDate.parse(view.getDocDate()));

                user.setDocument(document);
                document.setUser(user);
            }

            if (view.getCitizenshipCode() != null) {
                Optional<Country> country = countryDao.findByCitizenshipCode(view.getCitizenshipCode());
                user.setCountry(country.orElseThrow(() ->
                        new SaveException("No country with this citizenship code was found"))
                );
            }
            userDao.insert(user);
        } else {
            throw new SaveException("No office with this id was found");
        }
    }

    /**
     * {@inheritDoc}
     * @param user
     */
    @Transactional
    @Override
    public void update(UserUpdateView user) throws UpdateException {
        Optional<User> source = userDao.findById(user.getId());
        if (source.isPresent()) {
            User src = source.get();
            src.setFirstName(user.getFirstName());
            src.setPosition(user.getPosition());
            if (user.getSecondName() != null) {
                src.setSecondName(user.getSecondName());
            }
            if (user.getMiddleName() != null) {
                src.setMiddleName(user.getMiddleName());
            }
            if (user.getPhone() != null) {
                src.setPhone(user.getPhone());
            }
            if (user.getSecondName() != null) {
                src.setSecondName(user.getSecondName());
            }
            if (user.getCitizenshipCode() != null) {
                Optional<Country> country = countryDao.findByCitizenshipCode(user.getCitizenshipCode());
                country.ifPresent(src::setCountry);
            }

            if (user.getDocName() != null || user.getDocNumber() != null || user.getDocDate() != null) {
                Document document = src.getDocument();
                if (user.getDocName() != null) {
                    Optional<DocumentType> type = documentTypeDao.findByName(user.getDocName());
                    type.ifPresent(document::setType);
                }
                if (user.getDocNumber() != null) {
                    document.setNumber(user.getDocNumber());
                }
                if (user.getDocDate() != null) {
                    document.setDate(LocalDate.parse(user.getDocDate()));
                }
            }

            userDao.update(src);
        } else {
            throw new UpdateException("No user for this id was found");
        }
    }
}