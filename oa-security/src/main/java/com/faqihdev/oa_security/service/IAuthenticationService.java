package com.faqihdev.oa_security.service;


import com.faqihdev.oa.shared.data.dto.request.user.LoginRequest;
import com.faqihdev.oa.shared.data.dto.request.user.RegistrationRequest;
import com.faqihdev.oa_util_core.base.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;

import java.io.IOException;


public interface IAuthenticationService {

     HttpResponse<Object> register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);

     String validateToken(String token);

     HttpResponse<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

     void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}