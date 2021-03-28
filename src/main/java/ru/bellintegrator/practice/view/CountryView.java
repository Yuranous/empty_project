package ru.bellintegrator.practice.view;

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
