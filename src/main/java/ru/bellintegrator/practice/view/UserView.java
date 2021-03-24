package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Тип документа")
public class UserView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Имя", example = "Иван")
    public String firstName;

    @Size(max = 25)
    @ApiModelProperty(value = "Фамилия", example = "Иванов")
    public String secondName;

    @Size(max = 25)
    @ApiModelProperty(value = "Отчество", example = "Иванович")
    public String middleName;

    @NotEmpty(message = "office_id cannot be null")
    @ApiModelProperty(value = "Офис", example = "5")
    public Long officeId;

    @Size(max = 25)
    @NotEmpty(message = "position cannot be null")
    @ApiModelProperty(value = "Должность", example = "Разработчик")
    public String position;

    @Size(max = 10)
    @ApiModelProperty(value = "Телефон", example = "8005553535")
    public String phone;

    @ApiModelProperty(value = "Код документа", example = "637")
    public Long docCode;

    @Size(max = 150)
    @ApiModelProperty(value = "Наименование документа", example = "Паспорт Российской федерации")
    public String docName;

    @Size(max = 25)
    @ApiModelProperty(value = "Номер документа", example = "5554545454545")
    public String docNumber;

    @ApiModelProperty(value = "Дата выдачи документа", example = "11.11.2016")
    public String docDate;

    @Size(max = 25)
    @ApiModelProperty(value = "Код страны", example = "637")
    public Integer citizenshipCode;

    @Size(max = 25)
    @ApiModelProperty(value = "Статус", example = "true")
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
