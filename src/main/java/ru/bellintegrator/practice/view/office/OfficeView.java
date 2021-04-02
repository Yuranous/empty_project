package ru.bellintegrator.practice.view.office;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeView {

    public Long id;

    public String name;

    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return  "{id:" + id + "," +
                "name:" + name + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive +
                '}';
    }
}