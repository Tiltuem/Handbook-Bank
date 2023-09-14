package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exception.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/codes/accountRestriction")
@Slf4j
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
    public String addNewAccountRestrictionCode(@Valid AccountRestrictionCode accountRestrictionCode, BindingResult bindingResult, Model model) {
        if (accountRestrictionCodeService.getByCode(accountRestrictionCode.getCode()) != null) {
            bindingResult.addError(new ObjectError("accountRestrictionCode", "Ошибка: данный код уже существует"));
            log.warn("Ошибка при добавлении кода: данный код уже существует");
        }
        if (!bindingResult.hasErrors()) {
            accountRestrictionCode.setDeleted(false);
            accountRestrictionCodeService.save(accountRestrictionCode);
            log.info("Код добавлен");
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
        log.info("Код с id: " + id + " удален");

        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        if (accountRestrictionCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует") ;
        }
        AccountRestrictionCode accountRestrictionCode = accountRestrictionCodeService.getById(id);
        accountRestrictionCode.setCode(newCode);
        accountRestrictionCodeService.save(accountRestrictionCode);
        log.info("Код с id: " + id + " редактирован");

        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/recovery/{id}")
    public String recoveryAccountRestrictionCode(@PathVariable long id) {
        accountRestrictionCodeService.recoveryById(id);
        log.info("Код с id: " + id + " восстановлен");
        return "redirect:/codes/accountRestriction/0";
    }

    private void setModel(Model model, Page<AccountRestrictionCode> codes, AccountRestrictionCode accountRestrictionCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("accountRestrictionCode", accountRestrictionCode);
    }
}
