package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.AccountStatusCodeService;
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
@RequestMapping("/codes/accountStatus")
public class AccountStatusCodeController {
    @Autowired
    AccountStatusCodeService accountStatusCodeService;

    @GetMapping("/{page}")
    public String getAllAccountStatusCode(@PathVariable int page, Model model) {
        Page<AccountStatusCode> codes = accountStatusCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        return "codes/accountStatus";
    }

    @PostMapping("/add")
    public String addNewAccountStatusCode(AccountStatusCode accountStatusCode) {
        accountStatusCodeService.save(accountStatusCode);
        return "redirect:/directories/accountStatus/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        accountStatusCodeService.deleteViaId(id);
        return "redirect:/codes/accountStatus/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        AccountStatusCode accountStatusCode = accountStatusCodeService.getViaId(id);
        accountStatusCode.setCode(newCode);
        accountStatusCodeService.save(accountStatusCode);
        return "redirect:/codes/accountStatus/" + page;
    }
}
