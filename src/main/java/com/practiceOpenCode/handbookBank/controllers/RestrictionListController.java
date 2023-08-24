package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.RestrictionList;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.ParticipantInfoService;
import com.practiceOpenCode.handbookBank.services.RestrictionListService;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message-{messageId}/entry-{entryId}")
@RequiredArgsConstructor
@Controller
public class RestrictionListController {
    @Autowired
    RestrictionListService restrictionListService;
    @Autowired
    ParticipantInfoService participantInfoService;
    @Autowired
    AbstractCodeService<RestrictionCode> restrictionCodeService;

    @GetMapping("/restriction-list-edit")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String getRestrictionListById(@RequestParam long id, Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, restrictionListService.getById(id), page);
        return "update/updateRestrictionList";
    }

    @PostMapping("/restriction-list-delete/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRestrictionList(@PathVariable long id, @RequestParam String page) {
        restrictionListService.deleteById(id);
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/restriction-list-recovery/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryRestrictionList(@PathVariable long id, @RequestParam String page) {
        restrictionListService.recoveryById(id);
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-restriction-list")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String newRestrictionList(Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, new RestrictionList(), page);
        return "add/addRestrictionList";
    }

    @PostMapping("/restriction-list-add")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String addRestrictionList(@Valid RestrictionList restrictionList, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String restrictionCode, @RequestParam String page) {
        if (bindingResult.getErrorCount() == 1) {
            setCodes(restrictionList, restrictionCode);
            participantInfoService.updateById(entryId, restrictionList);
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }
        setModel(model, messageId, entryId, restrictionList, page);
        model.addAttribute("bindingResult", bindingResult);
        return "add/addRestrictionList";
    }

    @PostMapping("/restriction-list-edit")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateRestrictionList(@Valid RestrictionList restrictionList, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String restrictionCode, @RequestParam String page) {
        setCodes(restrictionList, restrictionCode);
        if (bindingResult.getErrorCount() == 1) {
            restrictionListService.save(restrictionList);
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, restrictionList, page);
        model.addAttribute("bindingResult", bindingResult);
        return "update/updateRestrictionList";
    }

    private void setModel(Model model, long messageId, long entryId, RestrictionList restrictionList, String page) {
        model.addAttribute("restrictionList", restrictionList);
        model.addAttribute("messageId", messageId);
        model.addAttribute("entryId", entryId);
        model.addAttribute("restrictionCodeList", restrictionCodeService.getAllCodeList());
        model.addAttribute("page", page);
    }

    private void setCodes(RestrictionList restrictionList, String restrictionCode) {
        restrictionList.setRestrictionCode(restrictionCodeService.getByCode(restrictionCode));
    }
}
