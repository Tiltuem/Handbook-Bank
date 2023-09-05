package com.practiceOpenCode.handbookBank.controllers.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "mainPages/homePage";
    }

    @GetMapping("/codes")
    public String getAllCodes() {
        return "mainPages/allDirectories";
    }
}
