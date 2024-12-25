package com.faqihdev.oa_security.service.impl;


import com.faqihdev.oa.shared.dao.user.IUserRepository;
import com.faqihdev.oa.shared.data.statval.constant.IApplicationConstant;
import com.faqihdev.oa_util_core.exception.UserNotActivatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USERNAME_NOT_FOUND));

        if (!user.getIsActive()) {
            try {
                throw new UserNotActivatedException(IApplicationConstant.StaticDefaultMessage.ExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION, IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION);
            } catch (UserNotActivatedException e) {
                throw new RuntimeException(e);
            }
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}