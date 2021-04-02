package ru.bellintegrator.practice.view.country;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountryView {

    public String name;

    public Integer citizenshipCode;

    @Override
    public String toString() {
        return "{name:" + name + "," +
                "code:" + citizenshipCode +
                "}";
    }
}
