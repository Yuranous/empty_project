package ru.bellintegrator.practice.view.organization;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationUpdateView {

    @NotNull(message = "ID value is required")
    public Long id;

    @NotNull(message = "Name value is required")
    public String name;

    @NotNull(message = "Fullname value is required")
    public String fullName;

    @NotNull(message = "INN value is required")
    @Size(min = 12, max = 12, message = "KPP must consist of 12 digits")
    public String inn;

    @NotNull(message = "KPP value is required")
    @Size(min = 9, max = 9, message = "KPP must consist of 9 digits")
    public String kpp;

    @NotNull(message = "Address value is required")
    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + "," +
                "name:" + name + "," +
                "fullName:" + fullName + "," +
                "inn:" + inn + "," +
                "kpp:" + kpp + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive + "," +
                '}';
    }
}
