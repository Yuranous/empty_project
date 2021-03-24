package ru.bellintegrator.practice.dao.user;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.User;

public interface UserDao {

    List<User> findAll(List<SearchCriteria> params, Long officeId);

    Optional<User> findById(Long id);

    void insert(User user);

    void update(User user);
}
