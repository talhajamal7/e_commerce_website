package com.bsse5a.EcommerceWeb.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/redirectingBasedOnRole")
    public String redirectAfterLogin(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
