package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Тип документа")
public class DocumentTypeView {

    @Size(max = 150)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование", example = "Паспорт гражданина Российской Федерации")
    public String name;

    @Min(0)
    @Max(100)
    @NotEmpty(message = "code cannot be null")
    @ApiModelProperty(value = "Код", example = "21")
    public Integer id;

    @Override
    public String toString() {
        return "{" +
                "name:" + name + "," +
                "code:" + id +
                "}";
    }
}
