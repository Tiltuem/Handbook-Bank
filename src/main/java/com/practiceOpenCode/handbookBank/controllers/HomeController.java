package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String hello() {
        return "mainPages/homePage";
    }

    @GetMapping("/codes")
    public String getAllDirectories() {
        return "mainPages/allDirectories";
    }
}
