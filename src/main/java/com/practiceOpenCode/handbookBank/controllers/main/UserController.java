package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.models.security.User;
import com.practiceOpenCode.handbookBank.services.security.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/profile")
    public String getUserInfo(Model model, @RequestParam(required = false) boolean success) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user  = userService.findByUsername(username).get();

        model.addAttribute("user", user);
        model.addAttribute("success", success);

        return "mainPages/profile";
    }
}
