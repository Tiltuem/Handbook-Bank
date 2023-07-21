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
        if(page > 0) {
            model.addAttribute("prevPage", page-1);
        } else {
            model.addAttribute("prevPage", page);
        }

        if(page < messages.getTotalPages()-1) {
            model.addAttribute("nextPage", page+1);
        } else {
            model.addAttribute("nextPage", page);
        }
        return "mainPages/allMessage";
    }

    /*@GetMapping("/message/${id}/${page}")
    public String getMessage(@PathVariable int page, Model model, @PathVariable int id) {
        Page<Message> messages = messageService.getAllMessages(PageRequest.of(page, 15, Sort.by("id")));
        model.addAttribute("messages", messages);
        model.addAttribute("maxPage",  messages.getTotalPages()-1);
        if(page > 0) {
            model.addAttribute("prevPage", page-1);
        } else {
            model.addAttribute("prevPage", page);
        }

        if(page < messages.getTotalPages()-1) {
            model.addAttribute("nextPage", page+1);
        } else {
            model.addAttribute("nextPage", page);
        }
        return "mainPages/message";
    }*/
    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        messageService.deleteViaId(id);
        return "redirect:/messages/" + page;
    }
}
