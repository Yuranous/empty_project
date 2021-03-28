package ru.bellintegrator.practice.view;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DocumentTypeView {

    @Size(max = 150)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @Min(0)
    @Max(100)
    @NotEmpty(message = "code cannot be null")
    public Integer code;

    @Override
    public String toString() {
        return "{" +
                "name:" + name + "," +
                "code:" + code +
                "}";
    }
}
