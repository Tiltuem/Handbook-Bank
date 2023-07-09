package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class FileImportController {
    @Autowired
    MessageService messageService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/import")
    public String save() {
        return "save";
    }

    @PostMapping("/import/date")
    public String saveMessageByDate(@RequestParam String date) {
        messageService.save(date);
        return "redirect:/";
    }

    @PostMapping("/import/file")
    public String saveMessageByFile(@RequestParam("fileXml") MultipartFile fileXml) {
        messageService.save(fileXml);
        return "redirect:/";
    }
}
