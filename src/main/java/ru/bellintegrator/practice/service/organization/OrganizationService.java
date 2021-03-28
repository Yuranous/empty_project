package ru.bellintegrator.practice.service.organization;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.OrganizationView;

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
    List<OrganizationView> organizations(List<SearchCriteria> params);

    /**
     * Получить организацию по идентификатору
     *
     * @param id идентификатор организации
     *
     * @return Информацию об организации
     */
    Optional<OrganizationView> organization(Long id);

    /**
     * Сохранить организацию
     *
     * @param organization информация о новой организации
     *
     * @return Статус процесса
     */
    boolean add(OrganizationView organization);

    /**
     * Обновить организацию
     *
     * @param organization информация об организации, которую необходимо обновить
     *
     * @return Статус процесса
     */
    boolean update(OrganizationView organization);
}
