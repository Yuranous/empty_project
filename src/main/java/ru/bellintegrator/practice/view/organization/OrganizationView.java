package ru.bellintegrator.practice.view.organization;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationView {

    public String name;

    public String fullName;

    public String inn;

    public String kpp;

    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return  "{name:" + name + "," +
                "fullName:" + fullName + "," +
                "inn:" + inn + "," +
                "kpp:" + kpp + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive + "," +
                '}';
    }
}
