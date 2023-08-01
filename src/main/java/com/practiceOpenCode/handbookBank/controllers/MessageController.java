package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/{page}")
    public String getAllMessages(@PathVariable int page, Model model) {
        Page<Message> messages = messageService.getAllMessages(PageRequest.of(page, 15, Sort.by("id")));
        model.addAttribute("messages", messages);
        model.addAttribute("maxPage",  messages.getTotalPages()-1);
        return "mainPages/allMessage";
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        messageService.deleteById(id);
        return "redirect:/messages/" + page;
    }
}
