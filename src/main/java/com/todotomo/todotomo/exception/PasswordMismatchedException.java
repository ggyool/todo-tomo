package com.todotomo.todotomo.exception;

public class PasswordMismatchedException extends RuntimeException{
    public PasswordMismatchedException(){
        super("password do not match");
    }

}
