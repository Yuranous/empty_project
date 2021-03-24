package ru.bellintegrator.practice.dao.office;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Office;

public interface OfficeDao {

    List<Office> findAll(List<SearchCriteria> params);

    Optional<Office> findById(Long id);

    void insert(Office office);

    void update(Office office);
}
