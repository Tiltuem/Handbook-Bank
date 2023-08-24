package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
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
    public String getAllEntries(@PathVariable int page, final Model model) {
        Page<Message> messages = messageService.getAllMessages(PageRequest.of(page, 10, Sort.by("id")));
        if (page > messages.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("messages", messages);
        model.addAttribute("page", page);
        return "mainPages/allMessage";
    }

    @GetMapping("/{page}-search")
    public String searchEntries(@RequestParam(required = false) String value,
                                @RequestParam(defaultValue = "false") Boolean showDeleted,
                                @RequestParam(required = false) String dateFrom,
                                @RequestParam(required = false) String dateBy,
                                @RequestParam(required = false) String column,
                                @RequestParam(required = false) String columnDate,
                                @PathVariable int page,
                                final Model model) {

        Page<Message> messages = messageService.searchMessages(PageRequest.of(page, 10, Sort.by("id")), value, showDeleted, column, columnDate, dateFrom, dateBy);
        if (page > messages.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("search", true);
        model.addAttribute("messages", messages);
        model.addAttribute("page", page);
        return "mainPages/allMessage";
    }

    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteMessage(@PathVariable long id, @RequestParam String page) {
        messageService.deleteById(id);
        return "redirect:/messages/" + page;
    }

    @PostMapping("/recovery/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryMessage(@PathVariable long id, @RequestParam String page) {
        messageService.recoveryById(id);
        return "redirect:/messages/" + page;
    }
}
