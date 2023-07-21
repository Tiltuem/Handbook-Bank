package com.practiceOpenCode.handbookBank.controllers.codes;


import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.services.codes.RestrictionCodeService;
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
@RequestMapping("/codes/restriction")
public class RestrictionCodeController {
    @Autowired
    RestrictionCodeService restrictionCodeService;

    @GetMapping("/{page}")
    public String getAllRestrictionCode(@PathVariable int page, Model model) {
        Page<RestrictionCode> codes = restrictionCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
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
        return "codes/restriction";
    }

    @PostMapping("/add")
    public String addNewRestrictionCode(RestrictionCode restrictionCode) {
        restrictionCodeService.save(restrictionCode);
        return "redirect:/codes/restriction/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        restrictionCodeService.deleteViaId(id);
        return "redirect:/codes/restriction/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        RestrictionCode restrictionCode = restrictionCodeService.getViaId(id);
        restrictionCode.setCode(newCode);
        restrictionCodeService.save(restrictionCode);
        return "redirect:/codes/restriction/" + page;
    }
}
