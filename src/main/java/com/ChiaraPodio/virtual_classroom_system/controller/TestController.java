package com.ChiaraPodio.virtual_classroom_system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    @PreAuthorize("permitAll()")
    public String publicEndpoint() {
        return "Public endpoint";
    }

    @GetMapping("/private")
    @PreAuthorize("isAuthenticated()")
    public String privateEndpoint() {
        return "Private endpoint";
    }

}
