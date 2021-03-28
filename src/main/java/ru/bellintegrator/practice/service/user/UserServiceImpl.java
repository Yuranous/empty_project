package ru.bellintegrator.practice.service.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.doctype.DocumentTypeDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.UserView;

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
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> users(List<SearchCriteria> params, Long officeId) {
        List<User> all = userDao.findAll(params, officeId);
        return all.stream().map(user -> {
            UserView res = mapperFacade.map(user, UserView.class);
            res.setOfficeId(user.getOffice().getId());
            Optional<Document> userDocument = Optional.ofNullable(user.getDocument());
            if (userDocument.isPresent()) {
                Document doc = userDocument.get();
                res.setDocName(doc.getType().getName());
                if (doc.getDate() != null) {
                    res.setDocDate(doc.getDate().toString());
                }
                res.setDocNumber(doc.getNumber());
            }
            if (user.getCountry() != null) {
                res.setCitizenshipCode(user.getCountry().getCitizenshipCode());
            }
            return res;
        }).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserView> user(Long id) {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            User user = result.get();
            UserView view = mapperFacade.map(user, UserView.class);
            view.setOfficeId(user.getOffice().getId());
            Document doc = user.getDocument();
            view.setDocName(doc.getType().getName());
            if (doc.getDate() != null) {
                view.setDocDate(doc.getDate().toString());
            }
            view.setDocNumber(doc.getNumber());
            view.setCitizenshipCode(user.getCountry().getCitizenshipCode());
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
    public boolean add(UserView view) {
        User user = mapperFacade.map(view, User.class);
        Optional<Office> office = officeDao.findById(view.getOfficeId());
        if (office.isPresent()) {
            user.setOffice(office.get());
            if (view.getDocCode() != null || view.getDocName() != null || view.getDocNumber() != null || view.getDocDate() != null) {
                Document document = new Document();
                document.setId(user.getId());
                Optional<DocumentType> typeOptional = documentTypeDao.findById(view.getDocCode());
                if (!typeOptional.isPresent()) {
                    if (view.getDocName() != null && view.getDocCode() != null) {
                        DocumentType type = new DocumentType();
                        type.setName(view.getDocName());
                        type.setCode(view.getDocCode());
                        typeOptional = Optional.of(type);
                    }
                }

                typeOptional.ifPresent(document::setType);

                if (view.getDocNumber() != null) {
                    document.setNumber(view.getDocNumber());
                }
                if (view.getDocDate() != null) {
                    document.setDate(LocalDate.parse(view.getDocDate()));
                }

                user.setDocument(document);
                document.setUser(user);
            }
            userDao.update(user);
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
    public boolean update(UserView user) {
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

            return true;
        }
        return false;
    }
}