package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

   /* @PostMapping("/create")
    public String createMessage(Message message) {
        messageService.saveMessage(message);
        return "redirect:/hello";
    }*/

}
