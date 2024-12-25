package com.faqihdev.oa_security.service;



import com.faqihdev.oa.shared.data.dto.response.user.GrantRoleAccessResponse;

import java.security.Principal;
import java.util.List;

public interface IAuthorizationService {
     GrantRoleAccessResponse giveAccessToUser(Long userId, Long roleId, Principal principal);

     List<String> getRolesByLoggedInUser(Principal principal);
}