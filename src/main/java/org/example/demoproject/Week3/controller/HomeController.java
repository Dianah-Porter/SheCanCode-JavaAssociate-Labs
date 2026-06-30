package org.example.demoproject.Week3.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User user) {

        if (user == null) {
            return "You are not logged in. Visit /login";
        }

        String name = user.getAttribute("name");

        if (name == null) {
            name = user.getAttribute("login");
        }

        String email = user.getAttribute("email");

        if (email == null) {
            email = "Email not available";
        }

        String provider =
                user.getAttribute("sub") != null ? "Google" : "GitHub";

        return "Welcome " + name +
                " (" + email + ")! You logged in with " + provider + ".";
    }
}