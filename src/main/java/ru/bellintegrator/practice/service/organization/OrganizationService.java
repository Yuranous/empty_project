package ru.bellintegrator.practice.service.organization;

import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.OrganizationView;

/**
 * Сервис справочника стран
 */
@Validated
public interface OrganizationService {

    /**
     * Получить список организаций
     *
     * @return {@Organization}
     */
    List<OrganizationView> organizations(List<SearchCriteria> params);

    /**
     * Получить организацию по идентификатору
     * @return
     */
    Optional<OrganizationView> organization(Long id);

    /**
     * Сохранить организацию
     */
    boolean add(OrganizationView organization);

    /**
     * Обновить организацию
     */
    boolean update(OrganizationView organization);
}
