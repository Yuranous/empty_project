package ru.bellintegrator.practice.view.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView {

    public Long id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String position;

    public String phone;

    public String docName;

    public String docNumber;

    public String docDate;

    public String citizenshipName;

    public Integer citizenshipCode;

    public Boolean isIdentified;

    @Override
    public String toString() {
        return  "{id:" + id + "," +
                "firstName:" + firstName + "," +
                "secondName:" + secondName + "," +
                "middleName:" + middleName + "," +
                "position:" + position + "," +
                "phone:" + phone + "," +
                "docName:" + docName + "," +
                "docNumber:" + docNumber + "," +
                "docDate:" + docDate + "," +
                "citizenshipName:" + citizenshipName + "," +
                "citizenshipCode:" + citizenshipCode + "," +
                "isIdentified:" + isIdentified +
                '}';
    }
}
