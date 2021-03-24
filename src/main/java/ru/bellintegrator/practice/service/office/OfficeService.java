package ru.bellintegrator.practice.service.office;

import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.OfficeView;

/**
 * Сервис справочника стран
 */
@Validated
public interface OfficeService {

    /**
     * Получить список организаций
     *
     * @return {@Office}
     */
    List<OfficeView> offices(List<SearchCriteria> params);

    /**
     * Получить организацию по идентификатору
     * @return
     */
    Optional<OfficeView> office(Long id);

    /**
     * Сохранить организацию
     */
    boolean add(OfficeView organization);

    /**
     * Обновить организацию
     */
    boolean update(OfficeView organization);
}
