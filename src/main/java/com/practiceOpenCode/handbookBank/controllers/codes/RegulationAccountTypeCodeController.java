package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.RegulationAccountTypeCodeService;
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
@RequestMapping("/codes/regulationAccountType")
public class RegulationAccountTypeCodeController {
    @Autowired
    RegulationAccountTypeCodeService regulationAccountTypeCodeService;

    @GetMapping("/{page}")
    public String getAllRegulationAccountTypeCode(@PathVariable int page, Model model) {
        Page<RegulationAccountTypeCode> codes = regulationAccountTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        if(page > 0) {
            model.addAttribute("prevPage", page-1);
        } else {
            model.addAttribute("prevPage", page);
        }

        if(page < codes.getTotalPages()-1) {
            model.addAttribute("nextPage", page+1);
        } else {
            model.addAttribute("nextPage", page);
        }
        return "codes/regulationAccountType";
    }

    @PostMapping("/add")
    public String addNewRegulationAccountTypeCode(RegulationAccountTypeCode regulationAccountTypeCode) {
        regulationAccountTypeCodeService.save(regulationAccountTypeCode);
        return "redirect:/codes/regulationAccountType/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        regulationAccountTypeCodeService.deleteViaId(id);
        return "redirect:/codes/regulationAccountType/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        RegulationAccountTypeCode regulationAccountTypeCode = regulationAccountTypeCodeService.getViaId(id);
        regulationAccountTypeCode.setCode(newCode);
        regulationAccountTypeCodeService.save(regulationAccountTypeCode);
        return "redirect:/codes/regulationAccountType/" + page;
    }

}
