package com.faqihdev.oa_security.service;

import com.indivira.ecommerceapi.dto.base.HttpResponse;
import com.indivira.ecommerceapi.dto.request.user.RequestUpdateUserDto;
import com.indivira.ecommerceapi.dto.response.user.ResponseDetailUserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IUserService {

    HttpResponse<List<ResponseDetailUserDto>> getListUsers();

    HttpResponse<Object> updateUsers(RequestUpdateUserDto request);

    HttpResponse<Object> deleteUsers(Long userId);

    HttpResponse<Object> getDetailUsers(HttpServletRequest request);
}
