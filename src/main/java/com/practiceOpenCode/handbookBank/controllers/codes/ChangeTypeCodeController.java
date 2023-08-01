package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.ChangeTypeCodeService;
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
@RequestMapping("/codes/changeType")
public class ChangeTypeCodeController {
    @Autowired
    ChangeTypeCodeService changeTypeCodeService;

    @GetMapping("/{page}")
    public String getAllChangeTypeCode(@PathVariable int page, Model model) {
        Page<ChangeTypeCode> codes = changeTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        return "codes/changeType";
    }

    @PostMapping("/add")
    public String addNewChangeTypeCode(ChangeTypeCode changeTypeCode) {
        changeTypeCodeService.save(changeTypeCode);
        return "redirect:/codes/changeType/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        changeTypeCodeService.deleteViaId(id);
        return "redirect:/codes/changeType/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ChangeTypeCode changeTypeCode = changeTypeCodeService.getViaId(id);
        changeTypeCode.setCode(newCode);
        changeTypeCodeService.save(changeTypeCode);
        return "redirect:/codes/changeType/" + page;
    }
}
