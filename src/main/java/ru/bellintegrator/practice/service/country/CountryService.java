package ru.bellintegrator.practice.service.country;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.CountryView;

/**
 * Сервис справочника стран
 */
@Validated
public interface CountryService {

    /**
     * Получить список стран из справочника
     *
     * @return {@Country}
     */
    List<CountryView> countries();
}
