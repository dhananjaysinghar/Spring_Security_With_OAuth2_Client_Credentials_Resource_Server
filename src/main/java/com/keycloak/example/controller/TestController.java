package com.keycloak.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('realspeed.admin')")
    public String hello() {
        return "hello keycloak-example";
    }
}
