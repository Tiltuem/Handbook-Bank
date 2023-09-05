package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.models.security.User;
import com.practiceOpenCode.handbookBank.services.security.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile")
    public String getUserInfo(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user  = userService.findByUsername(username).get();
        model.addAttribute("user", user);

        return "mainPages/profile";
    }
}
