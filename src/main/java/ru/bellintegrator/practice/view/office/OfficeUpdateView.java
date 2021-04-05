package ru.bellintegrator.practice.view.office;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeUpdateView {

    @NotNull(message = "ID value is required")
    public Long id;

    @NotNull(message = "Name value is required")
    @Size(max = 25)
    public String name;

    @NotNull(message = "Address value is required")
    @Size(max = 50, message = "Address must be no longer than 50 letters")
    public String address;

    @Size(min = 10, max = 10, message = "Phone must consist of 10 digits")
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