package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Офис")
public class OfficeView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Название", example = "Рассвет")
    public String name;

    @NotEmpty(message = "orgId cannot be null")
    @ApiModelProperty(value = "Организация", example = "1")
    public Long orgId;

    @Size(max = 50)
    @ApiModelProperty(value = "Адрес", example = "ул. Тверская, 1")
    public String address;

    @Size(max = 10)
    @ApiModelProperty(value = "Номер телефона", example = "8005553535")
    public String phone;

    @ApiModelProperty(value = "Статус", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{name:" + name + "," +
                "orgId:" + orgId + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive +
                '}';
    }
}
