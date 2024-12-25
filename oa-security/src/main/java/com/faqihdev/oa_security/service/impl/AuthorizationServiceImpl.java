package com.faqihdev.oa_security.service.impl;
import com.faqihdev.oa.shared.dao.user.IRoleRepository;
import com.faqihdev.oa.shared.dao.user.IUserRepository;
import com.faqihdev.oa.shared.data.dto.response.user.GrantRoleAccessResponse;
import com.faqihdev.oa.shared.data.model.auth.Role;
import com.faqihdev.oa.shared.data.model.auth.User;
import com.faqihdev.oa.shared.data.statval.enumeration.EUserRole;
import com.faqihdev.oa_security.service.IAuthorizationService;
import com.faqihdev.oa_util_core.exception.DataNotFoundException;
import com.faqihdev.oa_util_core.exception.RoleHasBeenAddedException;
import com.faqihdev.oa_util_core.exception.UnauthorizedGrantingAccessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    @Override
    @Transactional
    public GrantRoleAccessResponse giveAccessToUser(Long userId, Long roleId, Principal principal) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User is not found","Please check your request id"));
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        Role newRole = roleRepository.findById(roleId).orElseThrow(() -> new DataNotFoundException("Specified role does not exist","Please check your database"));
        if (user.getIsActive() == Boolean.TRUE && !activeRoles.contains(EUserRole.ROLE_ADMIN.getName())) {
            throw new UnauthorizedGrantingAccessException("You are not allowed to grant access role","User role is not granted");
        }
        if (user.getRoles().stream().anyMatch(role -> role.getId().equals(roleId))) {
            throw new RoleHasBeenAddedException(String.format("Role %s has been added to user %s",newRole.getRoleName(),user.getFirstName()),"No need to add role");
        }
        user.getRoles().add(newRole);
        userRepository.save(user);
        return GrantRoleAccessResponse
                .builder()
                .message(String.format("new role has been granted to user %s", user.getFirstName()))
                .roleName(newRole.getRoleName())
                .build();

    }


    @Override
    public List<String> getRolesByLoggedInUser(Principal principal) {
        Set<Role> roles = getLoggedInUser(principal).getRoles();
        return roles.stream()
                .map(Role::getRoleName)
                .toList();
    }

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByUserName(principal.getName()).get();
    }

}