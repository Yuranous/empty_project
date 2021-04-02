package ru.bellintegrator.practice.view.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListItemView {
    public Long id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String position;

    @Override
    public String toString() {
        return  "{id:" + id + "," +
                "firstName:" + firstName + "," +
                "secondName:" + secondName + "," +
                "middleName:" + middleName + "," +
                "position:" + position + "," +
                '}';
    }
}
