package ru.bellintegrator.practice.dao.office;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Office;

/**
 * DAO офиса
 */
public interface OfficeDao {

    /**
     * Получить список офисов
     *
     * @param params список критериев для поиска офисов
     *
     * @return Список офисов
     */
    List<Office> findAllBySearchCriteria(List<SearchCriteria> params);

    /**
     * Получить офис
     *
     * @param id идентификатор офиса
     *
     * @return Офис
     */
    Optional<Office> findById(Long id);

    /**
     * Сохранить офис
     *
     * @param office офис
     */
    void insert(Office office);

    /**
     * Обновить офис
     *
     * @param office офис
     */
    void update(Office office);
}
