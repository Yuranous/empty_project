package ru.bellintegrator.practice.view.doctype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentTypeView {

    public String name;

    public Integer code;

    @Override
    public String toString() {
        return "{" +
                "name:" + name + "," +
                "code:" + code +
                "}";
    }
}
