package com.todotomo.todotomo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Getter
public class UserResponseDto {

    private String email;
    private String name;

    @Builder
    public UserResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
