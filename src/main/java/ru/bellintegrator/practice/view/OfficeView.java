package ru.bellintegrator.practice.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeView {

    public Long id;

    public String name;

    public Long orgId;

    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "{name:" + name + "," +
                "orgId:" + orgId + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive +
                '}';
    }
}

//TODO: Проверить валидацию на контроллерах