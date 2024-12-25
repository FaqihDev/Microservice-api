package com.faqihdev.oa_security.endpoint;


import com.indivira.ecommerceapi.dto.base.HttpResponse;
import com.indivira.ecommerceapi.dto.request.user.GrantRoleAccessRequest;
import com.indivira.ecommerceapi.dto.response.user.GrantRoleAccessResponse;
import com.indivira.ecommerceapi.service.IAuthorizationService;
import com.indivira.ecommerceapi.statval.constant.IApplicationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHORIZATION, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationEndpoint {

    private final IAuthorizationService authorizationService;

    @PostMapping(IApplicationConstant.Path.Authorization.GRANT_ACCESS)
    ResponseEntity<?> grantAccess(@RequestBody GrantRoleAccessRequest request, Principal principal) {
        GrantRoleAccessResponse response = authorizationService.giveAccessToUser(request.getUserId(),request.getRoleId(), principal);
         return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .developerMessage(String.format("You are now %s", response.getRoleName()))
                        .message("Access has been granted")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build());
    }

}