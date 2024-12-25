package com.faqihdev.oa_security.service.impl;


import com.indivira.ecommerceapi.Mapper.user.ResponseUpdateUserMapper;
import com.indivira.ecommerceapi.Mapper.user.ResponseUserDetailMapper;
import com.indivira.ecommerceapi.dto.base.HttpResponse;
import com.indivira.ecommerceapi.dto.request.user.RequestUpdateUserDto;
import com.indivira.ecommerceapi.dto.response.user.ResponseDetailUserDto;
import com.indivira.ecommerceapi.dto.response.user.ResponseUpdateUserDto;
import com.indivira.ecommerceapi.exception.DataNotFoundException;
import com.indivira.ecommerceapi.exception.IllegalHeaderException;
import com.indivira.ecommerceapi.model.auth.User;
import com.indivira.ecommerceapi.repository.IUserRepository;
import com.indivira.ecommerceapi.service.IUserService;
import com.indivira.ecommerceapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage;
import com.indivira.ecommerceapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;
import com.indivira.ecommerceapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;
import com.indivira.ecommerceapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final ResponseUserDetailMapper userDetailMapper;
    private final ResponseUpdateUserMapper responseUpdateUserMapper;


    @Override
    public HttpResponse<List<ResponseDetailUserDto>> getListUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return HttpResponse.noContent();
        }

        List<ResponseDetailUserDto> listUsers = userDetailMapper.entitiesIntoDTOs(users);
        return HttpResponse.build(SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                listUsers);

    }

    @Override
    public HttpResponse<Object> updateUsers(RequestUpdateUserDto request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow(() ->  new DataNotFoundException(String.format("user with id %d is not exist", request.getUserId()),"please check again your user id"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);

        ResponseUpdateUserDto responseData =  responseUpdateUserMapper.convert(user);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                SuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                 HttpStatus.OK,
                 responseData);

        }

    @Override
    public HttpResponse<Object> deleteUsers(Long userId) {
        var userById = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(String.format("user with id %d is not exist", userId), "please check again your user id"));
        userById.setIsDeleted(true);
        userRepository.save(userById);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                SuccessMessage.DATA_DELETED_SUCCESSFULLY,
                HttpStatus.OK,
                null);
    }

    @Override
    public HttpResponse<Object> getDetailUsers(HttpServletRequest request)  {

        User user = null;
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                user = userRepository.findByToken(token);
                if (user == null) {
                    throw new JwtException(ExceptionMessage.AUTHORIZATION_HEADER_INVALID);
                }
            } catch (JwtException e) {
                HttpResponse.InvalidatedToken();
            }
            ResponseDetailUserDto dataUser = userDetailMapper.convert(user);
            return HttpResponse.build(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                    SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                    HttpStatus.OK,
                    dataUser);


        }
        throw new IllegalHeaderException(ExceptionMessage.AUTHORIZATION_HEADER_INVALID, DeveloperExceptionMessage.AUTHORIZATION_HEADER_INVALID);
    }

}
