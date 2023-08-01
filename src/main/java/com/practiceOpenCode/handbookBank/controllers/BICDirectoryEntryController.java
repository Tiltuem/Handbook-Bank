package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.ParticipantInfoService;
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
@RequestMapping("/message-{messageId}/directory-entry")
public class BICDirectoryEntryController {
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @GetMapping("/{page}")
    public String getAllMessages(@PathVariable int page, @PathVariable int messageId, Model model) {
        Page<BICDirectoryEntry> directoryEntries = bicDirectoryEntryService.getAllDirectory(PageRequest.of(page, 15, Sort.by("id")));
        model.addAttribute("directoryEntries", directoryEntries);
        model.addAttribute("messageId", messageId);
        model.addAttribute("maxPage",  directoryEntries.getTotalPages()-1);
        return "mainPages/directory-entry";
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        bicDirectoryEntryService.deleteViaId(id);
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }
}
