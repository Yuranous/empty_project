package ru.bellintegrator.practice.dao.user;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.User;

/**
 * DAO пользователя
 */
public interface UserDao {

    /**
     * Получить список пользователей
     *
     * @param params список критерией для поиска пользователей
     * @param officeId идентификатор офиса, в котором работают пользователи
     *
     * @return Список пользователей, удовлетворяющих критериям поиска
     */
    List<User> findAll(List<SearchCriteria> params, Long officeId);

    /**
     * Получить пользователя
     *
     * @param id идентификатор пользователя
     *
     * @return Пользователя
     */
    Optional<User> findById(Long id);

    /**
     * Добавить пользователя
     *
     * @param user пользователь
     */
    void insert(User user);

    /**
     * Обновить пользователя
     *
     * @param user пользователь
     */
    void update(User user);
}
