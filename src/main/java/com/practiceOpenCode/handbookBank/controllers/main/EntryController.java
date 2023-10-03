package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.*;
import com.practiceOpenCode.handbookBank.models.main.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import com.practiceOpenCode.handbookBank.services.main.BICDirectoryEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message-{messageId}/directory-entry")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@Slf4j
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
    public String getAllEntries(@PathVariable int page,
                                @PathVariable int messageId,
                                Model model) {
        Page<BICDirectoryEntry> entries =
                bicDirectoryEntryService.getAllEntries(PageRequest.of(page, 10, Sort.by("id")));

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
                                @RequestParam(name = "dateFrom", required = false) String dateFrom,
                                @RequestParam(name = "dateBy", required = false) String dateBy,
                                @RequestParam(required = false) String column,
                                @PathVariable int page,
                                @PathVariable int messageId,
                                Model model) {
        Page<BICDirectoryEntry> entries =
                bicDirectoryEntryService.searchEntries(PageRequest.of(page, 10, Sort.by("id")), value, showDeleted, column, dateFrom, dateBy);

        if (page > entries.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("entries", entries);
        model.addAttribute("page", page);
        model.addAttribute("messageId", messageId);
        model.addAttribute("search", true);

        return "mainPages/directory-entries";
    }

    @PostMapping("/delete/{id}")
    public String deleteEntry(@PathVariable long id, @RequestParam String page) {
        bicDirectoryEntryService.deleteById(id);

        log.info("Запись (id: " + id + ") уделена");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/recovery/{id}")
    public String recoveryEntry(@PathVariable long id, @RequestParam String page) {
        bicDirectoryEntryService.recoveryById(id);

        log.info("Аккаунт (id: " + id + ") восстановлена");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/edit-entry")
    public String getEntryById(@RequestParam long id,
                               @PathVariable String messageId,
                               @RequestParam String page,
                               Model model) {
        BICDirectoryEntry directoryEntry = bicDirectoryEntryService.getById(id);

        model.addAttribute("bicDirectoryEntry", directoryEntry);
        model.addAttribute("participantInfo", directoryEntry.getParticipantInfo());
        setModel(model, page, messageId);

        return "update/updateEntry";
    }

    @GetMapping("/new-entry")
    public String addEntry(@PathVariable String messageId,
                           @RequestParam String page,
                           Model model) {
        setModel(model, page, messageId);
        model.addAttribute("bicDirectoryEntry", new BICDirectoryEntry());
        model.addAttribute("participantInfo", new ParticipantInfo());

        return "add/addEntry";
    }

    @PostMapping("/add")
    public String addNewEntry(@Valid @ModelAttribute("bicDirectoryEntry") BICDirectoryEntry bicDirectoryEntry,
                              BindingResult bicDirectoryEntryBindingResult,
                              @Valid @ModelAttribute("participantInfo") ParticipantInfo participantInfo,
                              BindingResult participantInfoBindingResult,
                              String participantType,
                              String serviceCs,
                              String exchangeParticipant,
                              String participantStatus,
                              String changeType,
                              @PathVariable String messageId,
                              @RequestParam String page,
                              Model model) {
        if (!bicDirectoryEntryBindingResult.hasErrors() && !participantInfoBindingResult.hasErrors()) {

            bicDirectoryEntryService.add(bicDirectoryEntry, participantInfo, participantType, serviceCs, exchangeParticipant, participantStatus, changeType);
            log.info("Запись добавлена");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, page, messageId);
        model.addAttribute("bicDirectoryEntry", bicDirectoryEntry);
        model.addAttribute("bicDirectoryEntryBindingResult", bicDirectoryEntryBindingResult);
        model.addAttribute("participantInfoBindingResult", participantInfoBindingResult);

        return "add/addEntry";
    }

    @PostMapping("/update")
    public String updateEntry(@Valid @ModelAttribute("bicDirectoryEntry") BICDirectoryEntry bicDirectoryEntry,
                              BindingResult bicDirectoryEntryBindingResult,
                              @Valid @ModelAttribute("participantInfo") ParticipantInfo participantInfo,
                              BindingResult participantInfoBindingResult,
                              String participantType,
                              String serviceCs,
                              String exchangeParticipant,
                              String participantStatus,
                              String changeType,
                              @PathVariable String messageId,
                              @RequestParam String page,
                              Model model) {
        if (!bicDirectoryEntryBindingResult.hasErrors() && !participantInfoBindingResult.hasErrors()) {

            bicDirectoryEntryService.update(bicDirectoryEntry, participantInfo, participantType, serviceCs, exchangeParticipant, participantStatus, changeType);
            log.info("Запись (id: " + bicDirectoryEntry.getId() + ") редактирована");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, page, messageId);
        model.addAttribute("bicDirectoryEntry", bicDirectoryEntry);
        model.addAttribute("bicDirectoryEntryBindingResult", bicDirectoryEntryBindingResult);
        model.addAttribute("participantInfoBindingResult", participantInfoBindingResult);

        return "update/updateEntry";
    }

    private void setModel(Model model, String page, String messageId) {
        model.addAttribute("participantTypeCodeList", participantTypeCodeService.getAllCodeList());
        model.addAttribute("serviceCsCodeList", serviceCsCodeService.getAllCodeList());
        model.addAttribute("exchangeParticipantCodeList", exchangeParticipantCodeService.getAllCodeList());
        model.addAttribute("participantStatusCodeList", participantStatusCodeService.getAllCodeList());
        model.addAttribute("changeTypeCodeList", changeTypeCodeService.getAllCodeList());
        model.addAttribute("messageId", messageId);
        model.addAttribute("page", page);
    }

}
