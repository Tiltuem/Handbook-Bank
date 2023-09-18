package com.practiceOpenCode.handbookBank.controllers.main;


import com.practiceOpenCode.handbookBank.models.main.SWBICs;
import com.practiceOpenCode.handbookBank.services.main.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.main.SWBICsService;
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
public class SWBICsController {
    @Autowired
    SWBICsService SWBICsService;
    @Autowired
    BICDirectoryEntryService bicDirectoryEntryService;


    @GetMapping("/swbics-edit")
    public String getSWBICsById(@RequestParam long id, Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, SWBICsService.getById(id), page);
        return "update/updateSWBICs";
    }

    @PostMapping("/swbics-delete/{id}")
    public String deleteSWBICs(@PathVariable long id, @RequestParam String page) {
        SWBICsService.deleteById(id);
        log.info("Список ограничений (id: " + id + ") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/swbics-recovery/{id}")
    public String recoverySWBICs(@PathVariable long id, @RequestParam String page) {
        SWBICsService.recoveryById(id);
        log.info("Список ограничений (id: " + id + ") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-swbics")
    public String newSWBICs(Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, new SWBICs(), page);
        return "add/addSWBICs";
    }

    @PostMapping("/swbics-add")
    public String addSWBICs(@Valid SWBICs swbics,
                            BindingResult bindingResult,
                            Model model,
                            @PathVariable long entryId,
                            @PathVariable long messageId,
                            @RequestParam String page) {
        if (!bindingResult.hasErrors()) {
            bicDirectoryEntryService.updateById(entryId, swbics);
            log.info("Добавлен новый перечень БИК");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, swbics, page);
        model.addAttribute("bindingResult", bindingResult);

        return "add/addSWBICs";
    }

    @PostMapping("/swbics-edit")
    public String updateSWBICs(@Valid SWBICs swbics,
                               BindingResult bindingResult,
                               Model model,
                               @PathVariable long entryId,
                               @PathVariable long messageId,
                               @RequestParam String page) {
        if (!bindingResult.hasErrors()) {
            SWBICsService.update(swbics);
            log.info("Перечень БИК (id: " + swbics.getId() + ") редактирован");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, swbics, page);
        model.addAttribute("bindingResult", bindingResult);

        return "update/updateSWBICs";
    }

    private void setModel(Model model, long messageId, long entryId, SWBICs swbics, String page) {
        model.addAttribute("swbics", swbics);
        model.addAttribute("messageId", messageId);
        model.addAttribute("entryId", entryId);
        model.addAttribute("page", page);
    }
}
