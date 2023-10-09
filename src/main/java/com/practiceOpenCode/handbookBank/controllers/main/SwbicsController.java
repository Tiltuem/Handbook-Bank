package com.practiceOpenCode.handbookBank.controllers.main;


import com.practiceOpenCode.handbookBank.models.main.Swbics;
import com.practiceOpenCode.handbookBank.services.main.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.main.SwbicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message-{messageId}/entry-{entryId}")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@Slf4j
public class SwbicsController {
    @Autowired
    SwbicsService swbicsService;
    @Autowired
    BICDirectoryEntryService bicDirectoryEntryService;


    @GetMapping("/swbics-edit")
    public String getSwbicsById(@RequestParam long id,
                                @PathVariable long messageId,
                                @PathVariable long entryId,
                                @RequestParam String page,
                                Model model) {
        setModel(model, messageId, entryId, swbicsService.getById(id), page);

        return "update/updateSwbics";
    }

    @PostMapping("/swbics-delete/{id}")
    public String deleteSwbics(@PathVariable long id, @RequestParam String page) {
        swbicsService.deleteById(id);

        log.info("Список ограничений (id: " + id + ") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/swbics-recovery/{id}")
    public String recoverySwbics(@PathVariable long id, @RequestParam String page) {
        swbicsService.recoveryById(id);

        log.info("Список ограничений (id: " + id + ") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-swbics")
    public String newSwbics(@PathVariable long messageId,
                            @PathVariable long entryId,
                            @RequestParam String page,
                            Model model) {
        setModel(model, messageId, entryId, new Swbics(), page);

        return "add/addSwbics";
    }

    @PostMapping("/swbics-add")
    public String addSwbics(@Valid Swbics swbics,
                            BindingResult bindingResult,
                            @PathVariable long entryId,
                            @PathVariable long messageId,
                            @RequestParam String page,
                            Model model) {
        if (!bindingResult.hasErrors()) {
            bicDirectoryEntryService.updateById(entryId, swbics);

            log.info("Добавлен новый перечень БИК");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, swbics, page);
        model.addAttribute("bindingResult", bindingResult);

        return "add/addSwbics";
    }

    @PostMapping("/swbics-edit")
    public String updateSwbics(@Valid Swbics swbics,
                               BindingResult bindingResult,
                               @PathVariable long entryId,
                               @PathVariable long messageId,
                               @RequestParam String page,
                               Model model) {
        if (!bindingResult.hasErrors()) {
            swbicsService.update(swbics);

            log.info("Перечень БИК (id: " + swbics.getId() + ") редактирован");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, swbics, page);
        model.addAttribute("bindingResult", bindingResult);

        return "update/updateSwbics";
    }

    private void setModel(Model model, long messageId, long entryId, Swbics swbics, String page) {
        model.addAttribute("swbics", swbics);
        model.addAttribute("messageId", messageId);
        model.addAttribute("entryId", entryId);
        model.addAttribute("page", page);
    }
}
