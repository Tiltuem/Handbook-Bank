package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.CodesRepository;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;

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

import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/codes/accountRestriction")
public class AccountRestrictionCodeController {
    @Autowired
    AbstractCodeService<AccountRestrictionCode> accountRestrictionCodeService;

    @GetMapping("/{page}")
    public String getAllAccountRestrictionCode(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted, @PathVariable int page, final Model model) {
        Page<AccountRestrictionCode> codes = accountRestrictionCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);
        if (codes.isEmpty() && Objects.isNull(code))
            throw new NotFoundPageException("Страница не найдена");
        setModel(model, codes, new AccountRestrictionCode());
        model.addAttribute("search", code);
        return "codes/accountRestriction";
    }

    @PostMapping("/add")
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewAccountRestrictionCode(@Valid AccountRestrictionCode accountRestrictionCode, BindingResult bindingResult, Model model) {
        if (accountRestrictionCodeService.getByCode(accountRestrictionCode.getCode()) != null) {
            bindingResult.addError(new ObjectError("accountRestrictionCode", "Ошибка: данный код уже существует"));
        }
        if (!bindingResult.hasErrors()) {
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
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        accountRestrictionCodeService.deleteById(id);
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/update")
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        AccountRestrictionCode accountRestrictionCode = accountRestrictionCodeService.getById(id);
        accountRestrictionCode.setCode(newCode);
        accountRestrictionCodeService.save(accountRestrictionCode);
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/recovery/{id}")
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryAccountRestrictionCode(@PathVariable long id) {
        accountRestrictionCodeService.recoveryById(id);
        return "redirect:/codes/accountRestriction/0";
    }

    private void setModel(Model model, Page<AccountRestrictionCode> codes, AccountRestrictionCode accountRestrictionCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("accountRestrictionCode", accountRestrictionCode);
    }
}
