package com.faqihdev.oa_security.endpoint;



import com.faqihdev.oa.shared.data.dto.request.user.LoginRequest;
import com.faqihdev.oa.shared.data.dto.request.user.RegistrationRequest;
import com.faqihdev.oa.shared.data.statval.constant.IApplicationConstant;
import com.faqihdev.oa_security.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationEndpoint {

    private final IAuthenticationService authenticationService;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return ResponseEntity.ok(authenticationService.logout(request, response, authentication));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

}