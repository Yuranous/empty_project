package ru.bellintegrator.practice.service.office;

import java.util.List;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.view.office.OfficeListFilter;
import ru.bellintegrator.practice.view.office.OfficeListItemView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

/**
 * Сервис офисов
 */
public interface OfficeService {

    /**
     * Получить список офисов
     *
     * @param filter список критериев для поиска офисов
     *
     * @return Список информации об офисах
     */
    List<OfficeListItemView> finAllByFilter(OfficeListFilter filter);

    /**
     * Получить офис по идентификатору
     *
     * @param id идентификатор офиса
     *
     * @return Информацию об офисе
     */
    OfficeView findById(Long id) throws DataNotFoundException;

    /**
     * Сохранить организацию
     *
     * @param office информация о новом офиссе
     *
     */
    void save(OfficeSaveView office) throws SaveException;

    /**
     * Обновить организацию
     *
     * @param office информация об офисе, который необходимо обновить
     *
     */
    void update(OfficeUpdateView office) throws UpdateException;
}
