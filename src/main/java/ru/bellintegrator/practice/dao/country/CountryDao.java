package ru.bellintegrator.practice.dao.country;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.practice.model.Country;

/**
 * DAO для работы с Country
 */
public interface CountryDao extends JpaRepository<Country, Long> {

    Optional<Country> findByCitizenshipCode(Integer citizenshipCode);
}
