package com.todotomo.todotomo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SessionRequestDto {

    private String email;
    private String password;

    @Builder
    public SessionRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
