package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class OrganizationView {

    @NotEmpty
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @Size(max = 25)
    @NotEmpty(message = "full_name cannot be null")
    public String fullName;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    public String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    public String kpp;

    @Size(max = 50)
    public String address;

    @Size(max = 10)
    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "{name:" + name + "," +
                "fullName:" + fullName + "," +
                "inn:" + inn + "," +
                "kpp:" + kpp + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive + "," +
                '}';
    }
}
