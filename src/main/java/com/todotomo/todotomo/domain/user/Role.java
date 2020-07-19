package com.todotomo.todotomo.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.todotomo.todotomo.domain.task.OrderType;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;

import java.security.Provider;

public enum Role { //implements GrantedAuthority
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
    private String value;
    Role(String value){
        this.value = value;
    }
    @JsonCreator
    public static Role convert(String target){
        return Role.valueOf(target.toUpperCase());
    }
    @JsonValue
    public String getValue() {return value;}

//    @Override
//    public String getAuthority() {
//        return value;
//    }
}
