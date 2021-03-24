package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Страна")
public class CountryView {

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Название", example = "РФ")
    public String name;

    @Min(0)
    @Max(1000)
    @NotEmpty(message = "code cannot be null")
    @ApiModelProperty(value = "Код", example = "643")
    public Integer citizenshipCode;

    @Override
    public String toString() {
        return "{name:" + name + "," +
                "code:" + citizenshipCode +
                "}";
    }
}
