package com.fz.pedidosspringbootionic.services;

import com.fz.pedidosspringbootionic.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSevice {

    public static UserSS getAuthenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
