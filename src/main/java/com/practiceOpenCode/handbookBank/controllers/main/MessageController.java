package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.main.Message;
import com.practiceOpenCode.handbookBank.services.main.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private static final int SIZE_PAGE = 10;

    @GetMapping("/{page}")
    public String getAllEntries(@PathVariable int page, Model model) {
        Page<Message> messages = messageService.getAllMessages(PageRequest.of(page, SIZE_PAGE, Sort.by("id")));

        if (page > messages.getTotalPages()) {
            throw new NotFoundPageException("Страница не найдена");
        }

        model.addAttribute("messages", messages);
        model.addAttribute("page", page);

        return "mainPages/allMessage";
    }

    @GetMapping("/{page}-search")
    public String searchEntries(@RequestParam(required = false) String value,
                                @RequestParam(defaultValue = "false") Boolean deleted,
                                @RequestParam(required = false) String dateFrom,
                                @RequestParam(required = false) String dateBy,
                                @RequestParam(required = false) String column,
                                @RequestParam(required = false) String columnDate,
                                @PathVariable int page,
                                Model model) {
        Page<Message> messages = messageService.searchMessages(
                PageRequest.of(page, SIZE_PAGE, Sort.by("id")), value, deleted, column, columnDate, dateFrom, dateBy);

        if (page > messages.getTotalPages()) {
            throw new NotFoundPageException("Страница не найдена");
        }
        model.addAttribute("search", true);
        model.addAttribute("messages", messages);
        model.addAttribute("page", page);

        return "mainPages/allMessage";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable long id, @RequestParam String page) {
        messageService.deleteById(id);

        log.info("ЭС (id: " + id + ") удалено");
        return "redirect:/messages/" + page;
    }

    @PostMapping("/recovery/{id}")
    public String recoveryMessage(@PathVariable long id, @RequestParam String page) {
        messageService.recoveryById(id);

        log.info("ЭС (id: " + id + ") восстановлено");
        return "redirect:/messages/" + page;
    }
}
