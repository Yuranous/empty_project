package ru.bellintegrator.practice.controller.country;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.country.CountryService;
import ru.bellintegrator.practice.view.country.CountryView;

@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CountryView> countries() {
        return service.getAllCountries();
    }
}
