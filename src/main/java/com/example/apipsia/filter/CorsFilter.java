package com.example.apipsia.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CorsFilter implements Filter {
    @Value("${access.control.allow.origin:*}")
    private String accessControlAllowOrigin;
    @Value("${access.control.allow.methods:POST, GET, OPTIONS, DELETE, PUT}")
    private String accessControlAllowMethods;
    @Value("${access.control.allow.maxAge:3600}")
    private String accessControlMaxAge;
    @Value("${access.control.allow-headers:x-request-with, authorization, content-type}")
    private String accessControlAllowHeaders;
    @Value("${access.control.allow-credentials:true}")
    private String accessControlAllowCredentials;
    @Value("${access.control.expose-headers:Cache-Control, Content-Language, Content-Length, Content-Type, Expires, Last-Modified, Pragma}")
    private String accessControlExposeHeaders;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, accessControlAllowOrigin);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, accessControlAllowMethods);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, accessControlMaxAge);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, accessControlAllowHeaders);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, accessControlAllowCredentials);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, accessControlExposeHeaders);

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }
}