package ru.bellintegrator.practice.view.office;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeUpdateView {

    @NotNull(message = "ID value is required")
    public Long id;

    @NotNull(message = "Name value is required")
    public String name;

    @NotNull(message = "Address value is required")
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