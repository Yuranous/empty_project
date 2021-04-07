package ru.bellintegrator.practice.view.office;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeListFilter {

    public Long orgId;

    @Size(max = 25)
    public String name;

    @Size(max = 50, message = "Address must be no longer than 50 letters")
    public String address;

    @Size(min = 10, max = 10, message = "Phone must consist of 10 digits")
    public String phone;

    public Boolean isActive;
}
