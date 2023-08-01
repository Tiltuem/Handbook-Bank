package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.services.codes.AccountRestrictionCodeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;




@Controller
@RequiredArgsConstructor
@RequestMapping("/codes/accountRestriction")
public class AccountRestrictionCodeController {
    @Autowired
    AccountRestrictionCodeService accountRestrictionCodeService;

    @GetMapping("/{page}")
    public String getAllAccountRestrictionCode(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted, @PathVariable int page, final Model model) {
        Page<AccountRestrictionCode> codes = accountRestrictionCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);
        setModel(model, codes, new AccountRestrictionCode());
        model.addAttribute("search", code);
        return "codes/accountRestriction";
    }


    @PostMapping("/add")
    public String addNewAccountRestrictionCode(@Valid AccountRestrictionCode accountRestrictionCode, BindingResult bindingResult, Model model) {
        if(accountRestrictionCodeService.getByCode(accountRestrictionCode.getCode()) != null) {
            bindingResult.addError(new ObjectError("accountRestrictionCode", "Ошибка: данный код уже существует"));
        }
        if(!bindingResult.hasErrors()) {
            accountRestrictionCode.setDeleted(false);
            accountRestrictionCodeService.save(accountRestrictionCode);
            return "redirect:/codes/accountRestriction/0";
        }
        Page<AccountRestrictionCode> codes = accountRestrictionCodeService.getAllCodes(PageRequest.of(0, 5, Sort.by("id")), null, null);
        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, accountRestrictionCode);

        return "codes/accountRestriction";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        accountRestrictionCodeService.deleteById(id);
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        AccountRestrictionCode accountRestrictionCode = accountRestrictionCodeService.getById(id);
        accountRestrictionCode.setCode(newCode);
        accountRestrictionCodeService.save(accountRestrictionCode);
        return "redirect:/codes/accountRestriction/" + page;
    }
    @PostMapping("/recovery/{id}")
    public String recoveryAccountRestrictionCode(@PathVariable long id) {
        accountRestrictionCodeService.recoveryById(id);
        return "redirect:/codes/accountRestriction/0";
    }

    private void setModel(Model model, Page<AccountRestrictionCode> codes, AccountRestrictionCode accountRestrictionCode ) {
        model.addAttribute("codes", codes);
        model.addAttribute("accountRestrictionCode", accountRestrictionCode);
    }
}
