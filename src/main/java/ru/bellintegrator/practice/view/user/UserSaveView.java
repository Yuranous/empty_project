package ru.bellintegrator.practice.view.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveView {

    @NotNull(message = "OfficeId value is required")
    public Long officeId;

    @NotNull(message = "Firstname value is required")
    @Size(max = 25)
    public String firstName;

    @Size(max = 25)
    public String secondName;

    @Size(max = 25)
    public String middleName;

    @NotNull(message = "Position value is required")
    @Size(max = 25)
    public String position;

    @Size(min = 10, max = 10, message = "Phone must consist of 10 digits")
    public String phone;

    public Long docCode;

    public String docName;

    @Size(max = 25)
    public String docNumber;

    public String docDate;

    @Min(value = 0, message = "citizenshipCode must consist of 3 digits")
    @Max(value = 100, message = "citizenshipCode must consist of 3 digits")
    public Integer citizenshipCode;

    public Boolean isIdentified;

    @Override
    public String toString() {
        return  "officeId:" + officeId + "," +
                "firstName:" + firstName + "," +
                "secondName:" + secondName + "," +
                "middleName:" + middleName + "," +
                "position:" + position + "," +
                "phone:" + phone + "," +
                "docName:" + docName + "," +
                "docNumber:" + docNumber + "," +
                "docDate:" + docDate + "," +
                "citizenshipCode:" + citizenshipCode + "," +
                "isIdentified:" + isIdentified +
                '}';
    }
}
