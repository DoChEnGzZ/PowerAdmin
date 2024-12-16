package com.chengnianzhi.poweradmin_api.dto.common.table;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TableFieldType {
    String("string"),
    Boolean("boolean"),
    Time("time"),
    Password("password"),
    Association("association"),
    EnumNumber("enum_number"),
    EnumString("enum_string");
    @JsonValue
    private final String type;

    TableFieldType(String type) {
        this.type = type;
    }

}
