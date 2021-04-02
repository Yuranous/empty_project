package ru.bellintegrator.practice.view.office;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeListItemView {

    public Long id;

    public String name;

    public Boolean isActive;

    @Override
    public String toString() {
        return  "{id:" + id + "," +
                "name:" + name + "," +
                "isActive:" + isActive +
                '}';
    }

}
