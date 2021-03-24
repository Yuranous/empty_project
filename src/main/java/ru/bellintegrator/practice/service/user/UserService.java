package ru.bellintegrator.practice.service.user;

import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.UserView;

/**
 * Сервис справочника стран
 */
@Validated
public interface UserService {

    /**
     * Получить список пользователей
     *
     * @return {@User}
     */
    List<UserView> users(List<SearchCriteria> params, Long officeId);

    /**
     * Получить пользователя по идентификатору
     * @return
     */
    Optional<UserView> user(Long id);

    /**
     * Сохранить пользователя
     */
    boolean add(UserView organization);

    /**
     * Обновить пользователя
     */
    boolean update(UserView organization);
}
