package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.services.codes.AccountRestrictionCodeService;
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
@RequestMapping("/codes/accountRestriction")
public class AccountRestrictionCodeController {
    @Autowired
    AccountRestrictionCodeService accountRestrictionCodeService;

    @GetMapping("/{page}")
    public String getAllAccountRestrictionCode(@PathVariable int page, Model model) {
        Page<AccountRestrictionCode> codes = accountRestrictionCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
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

        return "codes/accountRestriction";
    }

    @PostMapping("/add")
    public String addNewAccountRestrictionCode(AccountRestrictionCode accountRestrictionCode) {
        accountRestrictionCodeService.save(accountRestrictionCode);
        return "redirect:/codes/accountRestriction/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        accountRestrictionCodeService.deleteViaId(id);
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        AccountRestrictionCode accountRestrictionCode = accountRestrictionCodeService.getViaId(id);
        accountRestrictionCode.setCode(newCode);
        accountRestrictionCodeService.save(accountRestrictionCode);
        return "redirect:/codes/accountRestriction/" + page;
    }
}
