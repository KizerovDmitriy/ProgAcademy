package com.example.gpthomework.controller;

import com.example.gpthomework.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeController() {

        return "Hello, world!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {

        return "If you see this, then you log in as user " + principal.getEmail()
                + " User ID: " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {

        return "If you see this, you are the admin " + principal.getEmail();
    }

}
