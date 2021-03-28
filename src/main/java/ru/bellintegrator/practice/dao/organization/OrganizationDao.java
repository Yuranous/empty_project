package ru.bellintegrator.practice.dao.organization;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Organization;

/**
 * DAO организации
 */
public interface OrganizationDao {

    /**
     * Получить список организаций
     *
     * @param params список критериев для поиска организаций
     *
     * @return Список организаций
     */
    List<Organization> findAll(List<SearchCriteria> params);

    /**
     * Получить организацию
     *
     * @param id идентификатор организации
     *
     * @return Организацию
     */
    Optional<Organization> findById(Long id);

    /**
     * Добавить организацию
     *
     * @param organization организация
     */
    void insert(Organization organization);

    /**
     * Обновить организацию
     *
     * @param organization организация
     */
    void update(Organization organization);
}
