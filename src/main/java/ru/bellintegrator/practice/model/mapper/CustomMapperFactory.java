package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.User;
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

        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter("dateConverter", new DateConverter());

        factory.classMap(User.class, UserView.class)
                .byDefault()
                .field("document.type.name", "docName")
                .field("document.date", "docDate")
                .field("document.number", "docNumber")
                .field("country.citizenshipCode", "citizenshipCode")
                .field("country.name", "citizenshipName")
                .register();

        factory.classMap(UserUpdateView.class, User.class)
                .byDefault()
                .field("docNumber", "document.number")
                .fieldMap("docDate", "document.date").converter("dateConverter").add()
                .register();

        factory.classMap(UserSaveView.class, User.class)
                .byDefault()
                .field("docNumber", "document.number")
                .fieldMap("docDate", "document.date").converter("dateConverter").add()
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
