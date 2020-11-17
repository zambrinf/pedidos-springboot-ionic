package com.fz.pedidosspringbootionic.resources;

import com.fz.pedidosspringbootionic.security.JWTUtil;
import com.fz.pedidosspringbootionic.security.UserSS;
import com.fz.pedidosspringbootionic.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PostMapping(value="/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserSevice.getAuthenticated();
        assert user != null;
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
