package com.faqihdev.oa_util_core.BaseMapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ConvertResponseEntity<TARGET> {

    private TARGET target;
    private ResponseEntity<?> responseEntity;

}