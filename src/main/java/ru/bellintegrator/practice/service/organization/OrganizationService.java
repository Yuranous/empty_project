package ru.bellintegrator.practice.service.organization;

import java.util.List;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.organization.OrganizationListItemView;
import ru.bellintegrator.practice.view.organization.OrganizationSaveView;
import ru.bellintegrator.practice.view.organization.OrganizationUpdateView;
import ru.bellintegrator.practice.view.organization.OrganizationView;

/**
 * Сервис организаций
 */
public interface OrganizationService {

    /**
     * Получить список организаций
     *
     * @param params список критериев для пиоска организаций
     *
     * @return Список информации об организациях
     */
    List<OrganizationListItemView> findAllBySearchCriteria(List<SearchCriteria> params);

    /**
     * Получить организацию по идентификатору
     *
     * @param id идентификатор организации
     *
     * @return Информацию об организации
     */
    OrganizationView findById(Long id) throws DataNotFoundException;

    /**
     * Сохранить организацию
     *
     * @param organization информация о новой организации
     *
     */
    void save(OrganizationSaveView organization);

    /**
     * Обновить организацию
     *
     * @param organization информация об организации, которую необходимо обновить
     *
     */
    void update(OrganizationUpdateView organization) throws UpdateException;
}
