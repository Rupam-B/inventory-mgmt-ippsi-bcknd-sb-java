package com.storeManagement.security;

import com.storeManagement.DTOs.UserDto;
import com.storeManagement.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    private UserServiceImpl userService;

    public boolean hasUserId(Long requiredUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return false;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        UserDto currentUser = userService.findUserIdByUsername(username);
        return requiredUserId.equals(currentUser.getUserId());
    }
}
