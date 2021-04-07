package ru.bellintegrator.practice.service.user;

import java.util.List;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.view.user.UserListFilter;
import ru.bellintegrator.practice.view.user.UserListItemView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

/**
 * Сервис пользователей
 */
public interface UserService {

    /**
     * Получить список пользователей
     *
     * @param filter список критерией для поиска пользователей
     * @return Список информации о пользователях
     */
    List<UserListItemView> findAllUsersByFilter(UserListFilter filter);

    /**
     * Получить пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     *
     * @return Информацию по пользователе
     */
    UserView findById(Long id) throws DataNotFoundException;

    /**
     * Сохранить пользователя
     *
     * @param user информация о новом пользователе
     *
     */
    void save(UserSaveView user) throws SaveException;

    /**
     * Обновить пользователя
     *
     * @param user информация о пользователе, которого необходимо обновить
     *
     */
    void update(UserUpdateView user) throws UpdateException;
}
