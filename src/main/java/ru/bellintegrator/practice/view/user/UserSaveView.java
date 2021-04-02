package ru.bellintegrator.practice.view.user;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveView {

    @NotNull(message = "Firstname value is required")
    public Long officeId;

    @NotNull(message = "Firstname value is required")
    public String firstName;

    public String secondName;

    public String middleName;

    @NotNull(message = "Position value is required")
    public String position;

    public String phone;

    public Long docCode;

    public String docName;

    public String docNumber;

    public String docDate;

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
