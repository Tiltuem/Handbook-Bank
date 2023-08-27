package com.practiceOpenCode.handbookBank.controllers;


import com.practiceOpenCode.handbookBank.models.SWBICs;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.SWBICsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message-{messageId}/entry-{entryId}")
@RequiredArgsConstructor
@Controller
@Slf4j
public class SWBICsController {
    @Autowired
    SWBICsService SWBICsService;
    @Autowired
    BICDirectoryEntryService bicDirectoryEntryService;


    @GetMapping("/swbics-edit")

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String getSWBICsById(@RequestParam long id, Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, SWBICsService.getById(id), page);
        return "update/updateSWBICs";
    }

    @PostMapping("/swbics-delete/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteSWBICs(@PathVariable long id, @RequestParam String page) {
        SWBICsService.deleteById(id);
        log.debug("Список ограничений (id: " + id + ") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/swbics-recovery/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoverySWBICs(@PathVariable long id, @RequestParam String page) {
        SWBICsService.recoveryById(id);
        log.debug("Список ограничений (id: " + id + ") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-swbics")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String newSWBICs(Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, new SWBICs(), page);
        return "add/addSWBICs";
    }

    @PostMapping("/swbics-add")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String addSWBICs(@Valid SWBICs swbics, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String page) {
        if (!bindingResult.hasErrors()) {
            bicDirectoryEntryService.updateById(entryId, swbics);
            log.debug("Добавлен новый перечень БИК");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }
        setModel(model, messageId, entryId, swbics, page);
        model.addAttribute("bindingResult", bindingResult);
        return "add/addSWBICs";
    }

    @PostMapping("/swbics-edit")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateSWBICs(@Valid SWBICs swbics, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String page) {
        if (!bindingResult.hasErrors()) {
            SWBICsService.save(swbics);
            log.debug("Перечень БИК (id: " + swbics.getId() + ") редактирован");
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
