package ru.bellintegrator.practice.service.user;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.UserView;

/**
 * Сервис пользователей
 */
public interface UserService {

    /**
     * Получить список пользователей
     *
     * @param params список критерией для поиска пользователей
     * @param officeId идентификатор офиса, в котором работают пользователи
     *
     * @return Список информации о пользователях
     */
    List<UserView> users(List<SearchCriteria> params, Long officeId);

    /**
     * Получить пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     *
     * @return Информацию по пользователе
     */
    Optional<UserView> user(Long id);

    /**
     * Сохранить пользователя
     *
     * @param user информация о новом пользователе
     *
     * @return Статус процесса
     */
    boolean add(UserView user);

    /**
     * Обновить пользователя
     *
     * @param user информация о пользователе, которого необходимо обновить
     *
     * @return Статус процесса
     */
    boolean update(UserView user);
}
