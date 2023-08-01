package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.InformationTypeCodeService;
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
@RequestMapping("/codes/informationType")
public class InformationTypeCodeController {
    @Autowired
    InformationTypeCodeService informationTypeCodeService;

    @GetMapping("/{page}")
    public String getAllInformationTypeCode(@PathVariable int page, Model model) {
        Page<InformationTypeCode> codes = informationTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        return "codes/informationType";
    }

    @PostMapping("/add")
    public String addNewInformationTypeCode(InformationTypeCode informationTypeCode) {
        informationTypeCodeService.save(informationTypeCode);
        return "redirect:/codes/informationType/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        informationTypeCodeService.deleteViaId(id);
        return "redirect:/codes/informationType/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        InformationTypeCode informationTypeCode = informationTypeCodeService.getViaId(id);
        informationTypeCode.setCode(newCode);
        informationTypeCodeService.save(informationTypeCode);
        return "redirect:/codes/informationType/" + page;
    }
}
