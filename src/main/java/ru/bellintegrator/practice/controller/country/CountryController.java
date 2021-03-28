package ru.bellintegrator.practice.controller.country;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.country.CountryService;
import ru.bellintegrator.practice.view.CountryView;

@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }


    @GetMapping
    public Map<String, List<CountryView>> countries() {
        Map<String, List<CountryView>> result = new HashMap<>();
        List<CountryView> countries = service.countries();
        if (!countries.isEmpty()) {
            result.put("data", countries);
        }
        return result;
    }
}
