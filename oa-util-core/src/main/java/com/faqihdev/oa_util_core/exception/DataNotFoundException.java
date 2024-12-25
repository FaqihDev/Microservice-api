package com.faqihdev.oa_util_core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException implements CustomException{


    private String exceptionMessage;
    private String developerMessage;


}