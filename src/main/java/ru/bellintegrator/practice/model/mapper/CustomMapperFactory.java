package ru.bellintegrator.practice.model.mapper;

import java.time.LocalDate;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.organization.OrganizationUpdateView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory factory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        factory.classMap(User.class, UserView.class)
                .byDefault()
                .customize(new CustomMapper<User, UserView>() {
                    @Override
                    public void mapAtoB(User user, UserView userView, MappingContext context) {
                        userView.setId(user.getId());
                        userView.setFirstName(user.getFirstName());
                        userView.setMiddleName(user.getMiddleName());
                        userView.setSecondName(user.getSecondName());
                        userView.setPosition(user.getPosition());
                        userView.setPhone(user.getPhone());
                        Document doc = user.getDocument();
                        DocumentType type = doc.getType();
                        userView.setDocName(type.getName());
                        userView.setDocDate(doc.getDate().toString());
                        userView.setDocNumber(doc.getNumber());
                        userView.setIsIdentified(user.getIsIdentified());
                        userView.setCitizenshipCode(user.getCountry().getCitizenshipCode());
                        userView.setCitizenshipName(user.getCountry().getName());
                    }
                })
                .register();
        factory.classMap(UserUpdateView.class, User.class)
                .byDefault()
                .customize(new CustomMapper<UserUpdateView, User>() {
                    @Override
                    public void mapAtoB(UserUpdateView userUpdateView, User user, MappingContext context) {
                        user.setFirstName(userUpdateView.getFirstName());
                        user.setSecondName(userUpdateView.getSecondName());
                        user.setMiddleName(userUpdateView.getMiddleName());
                        user.setId(userUpdateView.getId());
                        user.setIsIdentified(userUpdateView.getIsIdentified());
                        user.setPosition(userUpdateView.getPosition());
                        user.setPhone(userUpdateView.getPhone());
                        if (userUpdateView.getDocDate() != null ||
                                userUpdateView.getDocName() != null ||
                                userUpdateView.getDocNumber() != null) {

                            Document doc = new Document();
                            doc.setNumber(userUpdateView.getDocNumber());
                            String date = userUpdateView.getDocDate();
                            if (date != null) {
                                doc.setDate(LocalDate.parse(date));
                            }
                        }
                    }
                })
                .register();
        factory.classMap(UserSaveView.class, User.class)
                .byDefault()
                .customize(new CustomMapper<UserSaveView, User>() {
                    @Override
                    public void mapAtoB(UserSaveView userSaveView, User user, MappingContext context) {
                        user.setFirstName(userSaveView.getFirstName());
                        user.setSecondName(userSaveView.getSecondName());
                        user.setMiddleName(userSaveView.getMiddleName());
                        user.setIsIdentified(userSaveView.getIsIdentified());
                        user.setPosition(userSaveView.getPosition());
                        user.setPhone(userSaveView.getPhone());
                        if (userSaveView.getDocDate() != null ||
                                userSaveView.getDocName() != null ||
                                userSaveView.getDocNumber() != null) {

                            Document doc = new Document();
                            doc.setNumber(userSaveView.getDocNumber());
                            String date = userSaveView.getDocDate();
                            if (date != null) {
                                doc.setDate(LocalDate.parse(date));
                            }
                        }
                    }
                })
                .register();

        factory.classMap(OfficeUpdateView.class, Office.class)
                .byDefault()
                .customize(new CustomMapper<OfficeUpdateView, Office>() {
                    @Override
                    public void mapAtoB(OfficeUpdateView officeUpdateView, Office office, MappingContext context) {
                        office.setName(officeUpdateView.getName());
                        office.setAddress(officeUpdateView.getAddress());
                        office.setPhone(officeUpdateView.getPhone());
                        office.setIsActive(officeUpdateView.getIsActive());
                    }
                })
                .register();
        factory.classMap(OrganizationUpdateView.class, Organization.class)
                .byDefault()
                .customize(new CustomMapper<OrganizationUpdateView, Organization>() {
                    @Override
                    public void mapAtoB(OrganizationUpdateView organizationUpdateView, Organization organization, MappingContext context) {
                        organization.setName(organizationUpdateView.getName());
                        organization.setFullName(organizationUpdateView.getFullName());
                        organization.setInn(organizationUpdateView.getInn());
                        organization.setKpp(organizationUpdateView.getKpp());
                        organization.setAddress(organizationUpdateView.getAddress());
                        organization.setPhone(organizationUpdateView.getPhone());
                        organization.setIsActive(organizationUpdateView.getIsActive());
                    }
                })
                .register();
        return factory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
