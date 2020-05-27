package com.example.apipsia.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "not_test")
    public String notTest() {
        return "not test";
    }

    @PostMapping(value = "post_test")
    public String postTest(Authentication auth) {
        return auth.getPrincipal().toString();
    }
}