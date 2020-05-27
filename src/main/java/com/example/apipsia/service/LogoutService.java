package com.example.apipsia.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogoutService implements LogoutHandler {
    @Autowired
    private LoginService loginService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String nom = authentication.getPrincipal().toString();

        loginService.getActiveConnnections().remove(nom);

        log.info("connection for user {} dropped", nom);
    }
    
}