package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
@ApiModel(description = "Тип документа")
public class OrganizationView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Название", example = "Сбербанк")
    public String name;

    @Size(max = 25)
    @NotEmpty(message = "full_name cannot be null")
    @ApiModelProperty(value = "Название", example = "ПАО Сбербанк")
    public String fullName;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    @ApiModelProperty(value = "ИНН", example = "123456789012")
    public String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    @ApiModelProperty(value = "КПП", example = "123456789")
    public String kpp;

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
                "fullName:" + fullName + "," +
                "inn:" + inn + "," +
                "kpp:" + kpp + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive + "," +
                '}';
    }
}
