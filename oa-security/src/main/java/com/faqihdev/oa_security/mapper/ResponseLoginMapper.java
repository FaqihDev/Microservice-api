package com.faqihdev.oa_security.mapper;



import com.faqihdev.oa.shared.dao.user.IUserRepository;
import com.faqihdev.oa.shared.data.dto.response.user.ResponseLoginDto;
import com.faqihdev.oa.shared.data.model.auth.User;
import com.faqihdev.oa.shared.data.statval.constant.IApplicationConstant;
import com.faqihdev.oa_security.service.impl.JwtService;
import com.faqihdev.oa_util_core.BaseMapper.ADATAMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseLoginMapper extends ADATAMapper<User, ResponseLoginDto> {

    private final IUserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public ResponseLoginDto convert(User source) {

        var user = userRepository.findByEmail(source.getEmail()).orElseThrow(() -> new UsernameNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USER_NOT_FOUND_EXCEPTION));
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.refreshToken(user);

        return ResponseLoginDto
                .builder()
                .email(source.getEmail())
                .role(source.getRoles().stream().map(x -> x.getUserRole().getName())
                        .collect(Collectors.joining(", ")))
                .name(source.getFirstName() + " " + source.getLastName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
