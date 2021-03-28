package ru.bellintegrator.practice.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView {

    public Long id;

    public String firstName;

    public String secondName;

    public String middleName;

    public Long officeId;

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
        return "{firstName:" + firstName + "," +
                "secondName:" + secondName + "," +
                "middleName:" + middleName + "," +
                "officeId:" + officeId + "," +
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
