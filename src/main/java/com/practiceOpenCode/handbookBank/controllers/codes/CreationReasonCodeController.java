package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.CreationReasonCodeService;
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
@RequestMapping("/codes/creationReason")
public class CreationReasonCodeController {
    @Autowired
    CreationReasonCodeService creationReasonCodeService;

    @GetMapping("/{page}")
    public String getAllCreationReasonCode(@PathVariable int page, Model model) {
        Page<CreationReasonCode> codes = creationReasonCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        return "codes/creationReason";
    }

    @PostMapping("/add")
    public String addNewCreationReasonCode(CreationReasonCode creationReasonCode) {
        creationReasonCodeService.save(creationReasonCode);
        return "redirect:/codes/creationReason/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        creationReasonCodeService.deleteViaId(id);
        return "redirect:/codes/creationReason/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        CreationReasonCode creationReasonCode = creationReasonCodeService.getViaId(id);
        creationReasonCode.setCode(newCode);
        creationReasonCodeService.save(creationReasonCode);
        return "redirect:/codes/creationReason/" + page;
    }
}
