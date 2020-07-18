package com.todotomo.todotomo.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;

public enum TaskState {
    DOING("doing"), DONE("done");
    String value;
    TaskState(String value){
        this.value = value;
    }
    @JsonCreator
    public static TaskState convert(String target){
        return TaskState.valueOf(target.toUpperCase());
    }
    @JsonValue
    public String getValue() {
        return value;
    }
    public static boolean checkValidate(String target) {
        if(target==null) return true;
        return DOING.value.equals(target) ||
                DONE.value.equals(target);
    }

}
