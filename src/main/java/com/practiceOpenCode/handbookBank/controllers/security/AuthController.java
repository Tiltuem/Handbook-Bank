package com.practiceOpenCode.handbookBank.controllers.security;

import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        return "authorization/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "authorization/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "authorization/login";
    }

    @GetMapping("/access-error")
    public String accessError() {
        return "errors/accessError";
    }
}
