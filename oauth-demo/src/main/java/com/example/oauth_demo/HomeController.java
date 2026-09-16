package com.example.oauth_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null) {
            logger.info("Home page accessed by unauthenticated user.");
            model.addAttribute("authenticated", false);
        } else {
            String email = (String) principal.getAttribute("email");
            logger.info("Home page accessed by authenticated user: {}", email);
            model.addAttribute("authenticated", true);
        }
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        String name = (String) principal.getAttribute("name");
        String email = (String) principal.getAttribute("email");

        logger.info("Dashboard accessed successfully by user: {} ({})", name, email);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "dashboard";
    }
}