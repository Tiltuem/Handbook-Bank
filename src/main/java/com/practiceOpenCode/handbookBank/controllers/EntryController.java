package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.*;
import com.practiceOpenCode.handbookBank.models.codes.*;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-{messageId}/directory-entry")
public class EntryController {
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    private AbstractCodeService<ParticipantTypeCode> participantTypeCodeService;
    @Autowired
    private AbstractCodeService<ServiceCsCode> serviceCsCodeService;
    @Autowired
    private AbstractCodeService<ExchangeParticipantCode> exchangeParticipantCodeService;
    @Autowired
    private AbstractCodeService<ParticipantStatusCode> participantStatusCodeService;
    @Autowired
    private AbstractCodeService<ChangeTypeCode> changeTypeCodeService;


    @GetMapping("/{page}")
    public String getAllEntries(@PathVariable int page, @PathVariable int messageId, final Model model) {
        Page<BICDirectoryEntry> entries = bicDirectoryEntryService.getAllEntries(PageRequest.of(page, 10, Sort.by("id")));
        if (page > entries.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("entries", entries);
        model.addAttribute("page", page);
        model.addAttribute("messageId", messageId);
        return "mainPages/directory-entries";
    }

    @GetMapping("/{page}-search")
    public String searchEntries(@RequestParam(required = false) String value,
                                @RequestParam(defaultValue = "false") Boolean showDeleted,
                                @RequestParam(name = "participantInfo.dateFrom", required = false) String dateFrom,
                                @RequestParam(name = "participantInfo.dateBy", required = false) String dateBy,
                                @RequestParam(required = false) String column,
                                @PathVariable int page,
                                @PathVariable int messageId,
                                final Model model) {

        Page<BICDirectoryEntry> entries = bicDirectoryEntryService.searchEntries(PageRequest.of(page, 10, Sort.by("id")), value, showDeleted, column, dateFrom, dateBy);
        if (page > entries.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("entries", entries);
        model.addAttribute("page", page);
        model.addAttribute("messageId", messageId);
        model.addAttribute("search", true);
        return "mainPages/directory-entries";
    }

    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEntry(@PathVariable long id, @RequestParam String page) {
        bicDirectoryEntryService.deleteById(id);
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/recovery/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryEntry(@PathVariable long id, @RequestParam String page) {
        bicDirectoryEntryService.recoveryById(id);
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/edit-entry")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String getEntryById(@RequestParam long id, Model model, @PathVariable String messageId, @RequestParam String page) {
        model.addAttribute("messageId", messageId);
        model.addAttribute("page", page);
        BICDirectoryEntry directoryEntry = bicDirectoryEntryService.getById(id);
        model.addAttribute("bicDirectoryEntry", directoryEntry);
        model.addAttribute("participantTypeCodeList", participantTypeCodeService.getAllCodeList());
        model.addAttribute("serviceCsCodeList", serviceCsCodeService.getAllCodeList());
        model.addAttribute("exchangeParticipantCodeList", exchangeParticipantCodeService.getAllCodeList());
        model.addAttribute("participantStatusCodeList", participantStatusCodeService.getAllCodeList());
        model.addAttribute("changeTypeCodeList", changeTypeCodeService.getAllCodeList());
        return "update/updateEntry";
    }

    @GetMapping("/new-entry")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String addEntry(@PathVariable int messageId, Model model, @RequestParam String page) {
        model.addAttribute("messageId", messageId);
        model.addAttribute("page", page);
        model.addAttribute("participantTypeCodeList", participantTypeCodeService.getAllCodeList());
        model.addAttribute("serviceCsCodeList", serviceCsCodeService.getAllCodeList());
        model.addAttribute("exchangeParticipantCodeList", exchangeParticipantCodeService.getAllCodeList());
        model.addAttribute("participantStatusCodeList", participantStatusCodeService.getAllCodeList());
        model.addAttribute("changeTypeCodeList", changeTypeCodeService.getAllCodeList());
        return "add/addEntry";
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String add(String bic, ParticipantInfo info, String participantType, String serviceCs, String exchangeParticipant, String participantStatus, String changeType, @RequestParam String page) {
        bicDirectoryEntryService.add(bic,  info,  participantType,  serviceCs,  exchangeParticipant,  participantStatus,  changeType);

        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PatchMapping("/update")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateEntry(@Valid BICDirectoryEntry bicDirectoryEntry, String participantType, String serviceCs, String exchangeParticipant, String participantStatus, String changeType, Model model, BindingResult bindingResult, @PathVariable String messageId, @RequestParam String page) {
        if (!bindingResult.hasErrors()) {
            bicDirectoryEntryService.update( bicDirectoryEntry,  participantType,  serviceCs,  exchangeParticipant,  participantStatus,  changeType);
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        model.addAttribute("participantTypeCodeList", participantTypeCodeService.getAllCodeList());
        model.addAttribute("serviceCsCodeList", serviceCsCodeService.getAllCodeList());
        model.addAttribute("exchangeParticipantCodeList", exchangeParticipantCodeService.getAllCodeList());
        model.addAttribute("participantStatusCodeList", participantStatusCodeService.getAllCodeList());
        model.addAttribute("changeTypeCodeList", changeTypeCodeService.getAllCodeList());
        model.addAttribute("bicDirectoryEntry", bicDirectoryEntry);
        model.addAttribute("bindingResult", bindingResult);
        model.addAttribute("messageId", messageId);
        return "update/updateEntry";
    }
}
