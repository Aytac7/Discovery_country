//package com.example.discovery_country.controller.auth;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class RegisterController {
//
//    @GetMapping("/home")
//    public String home(Model model,
//                       @AuthenticationPrincipal OAuth2User user) {
//        String name = user.getAttribute("name");
//        String email = user.getAttribute("email");
//        model.addAttribute("name", name);
//        model.addAttribute("email", email);
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login"; // Return login page template
//    }
//
//    @GetMapping("/register")
//    public String showRegisterPage() {
//        return "register"; // Return register page template
//    }
//}
