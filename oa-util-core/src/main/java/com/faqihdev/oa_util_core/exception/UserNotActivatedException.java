package com.faqihdev.oa_util_core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;


@Getter
@Setter
public class UserNotActivatedException extends AuthenticationException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

    public UserNotActivatedException(String msg, Throwable cause, String developerMessage) {
        super(msg, cause);
        this.exceptionMessage = msg;
        this.developerMessage = developerMessage;
    }

    public UserNotActivatedException(String msg,String developerMessage) {
        super(msg);
        this.developerMessage = developerMessage;
    }
}