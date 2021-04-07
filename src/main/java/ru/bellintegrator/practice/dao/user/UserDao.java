package ru.bellintegrator.practice.dao.user;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserListFilter;

/**
 * DAO пользователя
 */
public interface UserDao {

    /**
     * Получить список пользователей
     *
     * @param filter список критерией для поиска пользователей
     * @return Список пользователей, удовлетворяющих критериям поиска
     */
    List<User> findAllByFilter(UserListFilter filter);

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
