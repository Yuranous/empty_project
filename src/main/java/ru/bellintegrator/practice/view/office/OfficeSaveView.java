package ru.bellintegrator.practice.view.office;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeSaveView {

    @NotNull(message = "OrgId value is required")
    public Long orgId;

    public String name;

    public String address;

    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return  "{orgId:" + orgId + "," +
                "name:" + name + "," +
                "address:" + address + "," +
                "phone:" + phone + "," +
                "isActive:" + isActive +
                '}';
    }
}