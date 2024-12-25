package com.faqihdev.oa_security.mapper;


import com.indivira.ecommerceapi.BaseMapper.ADATAMapper;
import com.indivira.ecommerceapi.dto.response.user.ResponseRegistrationDto;
import com.indivira.ecommerceapi.model.auth.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ResponseRegistrationMapper extends ADATAMapper<User, ResponseRegistrationDto> {
    @Override
    public ResponseRegistrationDto convert(User source) {
        return ResponseRegistrationDto
                .builder()
                .email(source.getEmail())
                .role(source.getRoles().stream().map(x -> x.getUserRole().getName())
                        .collect(Collectors.joining(", ")))
                .name(source.getFirstName() + " " + source.getLastName())
                .build();
    }
}
