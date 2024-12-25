package com.faqihdev.oa_security.service.impl;

import com.faqihdev.oa.shared.dao.user.IRoleRepository;
import com.faqihdev.oa.shared.dao.user.ITokenRepository;
import com.faqihdev.oa.shared.dao.user.IUserRepository;
import com.faqihdev.oa.shared.data.dto.request.user.LoginRequest;
import com.faqihdev.oa.shared.data.dto.request.user.RegistrationRequest;
import com.faqihdev.oa.shared.data.dto.response.user.ResponseLoginDto;
import com.faqihdev.oa.shared.data.model.auth.Role;
import com.faqihdev.oa.shared.data.model.auth.User;
import com.faqihdev.oa.shared.data.statval.constant.IApplicationConstant;
import com.faqihdev.oa.shared.data.statval.enumeration.EUserRole;
import com.faqihdev.oa_security.mapper.ResponseLoginMapper;
import com.faqihdev.oa_security.mapper.ResponseRegistrationMapper;
import com.faqihdev.oa_util_core.base.HttpResponse;
import com.faqihdev.oa_util_core.exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.faqihdev.oa_security.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final ITokenRepository tokenRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ResponseRegistrationMapper responseRegistrationMapper;
    private final ResponseLoginMapper loginMapper;
    private final LogoutServiceImpl logoutService;

    @Override
    @Transactional
    public HttpResponse<Object> register(RegistrationRequest request) {

        Set<Role>roles = new HashSet<>();
        EUserRole role = EUserRole.ROLE_USER;
        try {
            Role userRole = roleRepository.findByUserRole(role);
            if (Objects.nonNull(userRole)) {
                roles.add(userRole);
            }
        } catch (DataNotFoundException e) {
            log.error("Error find Role by Code {} : {} %s".formatted(EUserRole.ROLE_USER), e.toString());
            throw new DataNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.ROLE_NOT_FOUND,DeveloperExceptionMessage.ROLE_NOT_FOUND);
        }
           var userByEmail = userRepository.findByUserName(request.getEmail());
           if (userByEmail.isPresent()) {
               throw new UserAlreadyExistException(ExceptionMessage.EMAIL_ALREADY_TAKEN,DeveloperExceptionMessage.EMAIL_ALREADY_TAKEN);
           }
               var user = User.builder()
                       .firstName(request.getFirstName())
                       .lastName(request.getLastName())
                       .email(request.getEmail())
                       .password(passwordEncoder.encode(request.getPassword()))
                       .roles(roles)
                       .isActive(true)
                       .gender(EGender.valueOf(request.getGender()))
                       .build();
               roleRepository.saveAll(roles);
               var currentUser = userRepository.save(user);
               var jwtToken = jwtService.generateToken(currentUser);
               saveUserToken(currentUser,jwtToken);

               ResponseRegistrationDto data = responseRegistrationMapper.convert(user);
               return HttpResponse.build(DeveloperSuccessMessage.LOGIN_SUCCESSFUL,SuccessMessage.LOGIN_SUCCESSFUL,HttpStatus.CREATED,data);

    }

    @Override
    public void saveUserToken(User user, String token) {
        var userToken = Token.builder()
                .user(user)
                .token(token)
                .tokenType(ETokenType.BEARER)
                .isTokenExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(userToken);
    }

    @Override
    public HttpResponse<Object> login (LoginRequest loginRequest){
       try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
       } catch (UserNotActivatedException e) {
           log.info("Authentication failed : {} ", e.getMessage());
           throw new UserNotActivatedException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION, IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION);
       } catch (org.springframework.security.authentication.BadCredentialsException e) {
           throw new BadCredentialsException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.BAD_CREDENTIALS, IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage.BAD_CREDENTIALS);
       } catch (UserNotFoundException e) {
           throw new UserNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USER_NOT_FOUND_EXCEPTION);
       }

       var user = userRepository.findByEmail(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USER_NOT_FOUND_EXCEPTION));
       String accessToken = jwtService.generateToken(user);
       ResponseLoginDto data = loginMapper.convert(user);

        revokeAllUserToken(user);
        saveUserToken(user,accessToken);
        return HttpResponse.build(IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage.LOGIN_SUCCESSFUL, IApplicationConstant.StaticDefaultMessage.SuccessMessage.LOGIN_SUCCESSFUL,HttpStatus.OK,data);
    }

    @Override
    public String validateToken(String token) {
        var tokenVerified = tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.TOKEN_IS_INVALID, IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage.TOKEN_IS_INVALID));
        var user = tokenVerified.getUser();
        if (!jwtService.isTokenValid(tokenVerified.token, user)) {
            throw new InvalidTokenException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.TOKEN_IS_INVALID, IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage.TOKEN_IS_INVALID);
        }

        user.setIsActive(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public HttpResponse<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request,response,authentication);
        return HttpResponse.build(IApplicationConstant.StaticDefaultMessage.SuccessMessage.LOGOUT_MSG_SUCCESS
                , IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage.LOGOUT_MSG_SUCCESS,HttpStatus.OK,HttpStatus.OK);
    }


    private void revokeAllUserToken(User user) {
        var validTokenUsers = tokenRepository.findAllByValidToken(user.getId());
        if (validTokenUsers.isEmpty()) {
            return;
        }
        validTokenUsers.forEach(token -> {
            token.setTokenExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokenUsers);
    }


    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith(" Bearer")) {
            return;
        }

        //get refresh token from header
        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USERNAME_NOT_FOUND));
            //get valid token
            if (jwtService.isTokenValid(refreshToken,user)) {
                var accessToken  = jwtService.generateToken(user);
                revokeAllUserToken(user);
                saveUserToken(user,accessToken);
                var authResponse = ResponseLoginDto
                        .builder()
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}