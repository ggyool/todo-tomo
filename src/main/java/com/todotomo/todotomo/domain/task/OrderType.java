package com.todotomo.todotomo.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;

public enum OrderType {
    ASC("asc"), DESC("desc");
    String value;
    OrderType(String value){
        this.value = value;
    }
    @JsonCreator
    public static OrderType convert(String target){
        return OrderType.valueOf(target.toUpperCase());
    }
    @JsonValue
    public String getValue() {
        return value;
    }
    public static boolean checkValidate(String target) {
        return ASC.value.equals(target) ||
                DESC.value.equals(target);
    }
}
