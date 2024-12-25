package com.faqihdev.oa_util_core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Setter
@Getter
public class UserAlreadyExistException extends AuthenticationException implements CustomException {


    private String exceptionMessage;
    private String developerMessage;

    public UserAlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAlreadyExistException(String msg,String developerMessage) {
        super(msg);
        this.developerMessage  = developerMessage;
    }


}