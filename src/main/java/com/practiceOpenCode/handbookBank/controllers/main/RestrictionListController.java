package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import com.practiceOpenCode.handbookBank.services.main.ParticipantInfoService;
import com.practiceOpenCode.handbookBank.services.main.RestrictionListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message-{messageId}/entry-{entryId}")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class RestrictionListController {
    private final RestrictionListService restrictionListService;
    private final ParticipantInfoService participantInfoService;
    private final AbstractCodeService<RestrictionCode> restrictionCodeService;

    @GetMapping("/restriction-list-edit")
    public String getRestrictionListById(@RequestParam long id,
                                         @PathVariable long messageId,
                                         @PathVariable long entryId,
                                         @RequestParam String page,
                                         Model model) {
        setModel(model, messageId, entryId, restrictionListService.getById(id), page);

        return "update/updateRestrictionList";
    }

    @PostMapping("/restriction-list-delete/{id}")
    public String deleteRestrictionList(@PathVariable long id, @RequestParam String page) {
        restrictionListService.deleteById(id);

        log.info("Список ограничений (id: " + id + ") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/restriction-list-recovery/{id}")
    public String recoveryRestrictionList(@PathVariable long id, @RequestParam String page) {
        restrictionListService.recoveryById(id);

        log.info("Список ограничений (id: " + id + ") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-restriction-list")
    public String newRestrictionList(@PathVariable long messageId,
                                     @PathVariable long entryId,
                                     @RequestParam String page,
                                     Model model) {
        setModel(model, messageId, entryId, new RestrictionList(), page);

        return "add/addRestrictionList";
    }

    @PostMapping("/restriction-list-add")
    public String addRestrictionList(@Valid RestrictionList restrictionList,
                                     BindingResult bindingResult,
                                     @PathVariable long entryId,
                                     @PathVariable long messageId,
                                     @RequestParam String restrictionCode,
                                     @RequestParam String page,
                                     Model model) {
        if (bindingResult.getErrorCount() == 1) {
            setCodes(restrictionList, restrictionCode);

            participantInfoService.updateById(entryId, restrictionList);
            log.info("Добавлен новый список ограничений");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, restrictionList, page);
        model.addAttribute("bindingResult", bindingResult);

        return "add/addRestrictionList";
    }

    @PostMapping("/restriction-list-edit")
    public String updateRestrictionList(@Valid RestrictionList restrictionList,
                                        BindingResult bindingResult,
                                        @PathVariable long entryId,
                                        @PathVariable long messageId,
                                        @RequestParam String restrictionCode,
                                        @RequestParam String page,
                                        Model model) {
        setCodes(restrictionList, restrictionCode);

        if (bindingResult.getErrorCount() == 1) {
            restrictionListService.update(restrictionList);

            log.info("Список ограничений (id: " + restrictionList.getId() + ") редактирован");
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
