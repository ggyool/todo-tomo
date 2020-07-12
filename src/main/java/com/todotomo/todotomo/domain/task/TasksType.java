package com.todotomo.todotomo.domain.task;

import io.swagger.annotations.ApiModel;

public enum TasksType {
    ALL("all"), DOING("doing"), DONE("done");
    String value;
    TasksType(String value){
        this.value = value;
    }
    public static TasksType convert(String target){
        return TasksType.valueOf(target.toUpperCase());
    }
    public static boolean checkValidate(String target) {
        return ALL.value.equals(target) ||
                DOING.value.equals(target) ||
                DONE.value.equals(target);
    }
}
