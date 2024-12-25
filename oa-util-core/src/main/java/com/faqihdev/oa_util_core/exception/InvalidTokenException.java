package com.faqihdev.oa_util_core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidTokenException extends RuntimeException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

}