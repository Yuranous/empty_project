package ru.bellintegrator.practice.dao.office;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeListFilter;

/**
 * DAO офиса
 */
public interface OfficeDao {

    /**
     * Получить список офисов
     *
     *
     * @param filter@return Список офисов
     */
    List<Office> finAllByFilter(OfficeListFilter filter);

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
