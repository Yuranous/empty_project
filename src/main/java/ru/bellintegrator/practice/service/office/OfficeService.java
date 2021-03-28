package ru.bellintegrator.practice.service.office;

import java.util.List;
import java.util.Optional;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.view.OfficeView;

/**
 * Сервис офисов
 */
public interface OfficeService {

    /**
     * Получить список офисов
     *
     * @param params список критериев для поиска офисов
     *
     * @return Список информации об офисах
     */
    List<OfficeView> offices(List<SearchCriteria> params);

    /**
     * Получить офис по идентификатору
     *
     * @param id идентификатор офиса
     *
     * @return Информацию об офисе
     */
    Optional<OfficeView> office(Long id);

    /**
     * Сохранить организацию
     *
     * @param office информация о новом офиссе
     *
     * @return Статус процесса
     */
    boolean add(OfficeView office);

    /**
     * Обновить организацию
     *
     * @param office информация об офисе, который необходимо обновить
     *
     * @return Статус процесса
     */
    boolean update(OfficeView office);
}
