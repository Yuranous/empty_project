package ru.bellintegrator.practice.dao.country;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.practice.model.Country;

/**
 * DAO страны
 */
public interface CountryDao extends JpaRepository<Country, Long> {

    /**
     * Получить страну по коду гражданина
     *
     * @param citizenshipCode Код гражданина
     *
     * @return Страну
     */
    Optional<Country> findByCitizenshipCode(Integer citizenshipCode);
}
