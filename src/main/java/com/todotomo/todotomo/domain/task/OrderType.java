package com.todotomo.todotomo.domain.task;

import io.swagger.annotations.ApiModel;

public enum OrderType {
    ASC("asc"), DESC("desc");
    String value;
    OrderType(String value){
        this.value = value==null?"asc":value;
    }
    public static OrderType convert(String target){
        return OrderType.valueOf(target.toUpperCase());
    }
    public static boolean checkValidate(String target) {
        return ASC.value.equals(target) ||
                DESC.value.equals(target);
    }
}
