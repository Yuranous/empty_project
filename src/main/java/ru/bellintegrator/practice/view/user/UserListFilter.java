package ru.bellintegrator.practice.view.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListFilter {

    @NotNull(message = "OfficeId value is required")
    public Long officeId;

    @Size(max = 25)
    public String firstName;

    @Size(max = 25)
    public String secondName;

    @Size(max = 25)
    public String middleName;

    @Size(max = 25)
    public String position;

    public Long docCode;

    @Min(value = 0, message = "citizenshipCode must be less than 100")
    @Max(value = 100, message = "citizenshipCode must be less than 100")
    public Integer citizenshipCode;


    @Override
    public String toString() {
        return  "officeId:" + officeId + "," +
                "firstName:" + firstName + "," +
                "secondName:" + secondName + "," +
                "middleName:" + middleName + "," +
                "position:" + position + "," +
                "citizenshipCode:" + citizenshipCode + "," +
                '}';
    }
}
