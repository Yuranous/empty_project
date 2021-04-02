package ru.bellintegrator.practice.service.country;

import java.util.List;
import ru.bellintegrator.practice.view.country.CountryView;

/**
 * Сервис справочника стран
 */
public interface CountryService {

    /**
     * Получить список стран из справочника
     *
     * @return Список информации о странах
     */
    List<CountryView> getAllCountries();
}
