package ru.bellintegrator.practice.dao.organization;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationListFilter;

/**
 * DAO организации
 */
public interface OrganizationDao {

    /**
     * Получить список организаций
     *
     *
     * @param filter@return Список организаций
     */
    List<Organization> findAllByFilter(OrganizationListFilter filter);

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
