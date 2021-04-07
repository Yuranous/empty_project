package ru.bellintegrator.practice.view.organization;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationListFilter {

    @NotNull(message = "Name value is required")
    @Size(max = 25)
    public String name;

    @Size(max = 250)
    public String fullName;

    @Size(min = 12, max = 12, message = "INN must consist of 12 digits")
    public String inn;

    @Size(min = 9, max = 9, message = "KPP must consist of 9 digits")
    public String kpp;

    @Size(max = 50, message = "Address must be no longer than 50 letters")
    public String address;

    @Size(min = 10, max = 10, message = "Phone must consist of 10 digits")
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
