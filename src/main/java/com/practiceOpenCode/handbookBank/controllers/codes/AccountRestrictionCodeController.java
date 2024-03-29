package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@Controller
@RequestMapping("/codes/accountRestriction")
@RequiredArgsConstructor
@Slf4j
public class AccountRestrictionCodeController {
    private final AbstractCodeService<AccountRestrictionCode> accountRestrictionCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllAccountRestrictionCode(@RequestParam(required = false) String code,
                                               @RequestParam(defaultValue = "false") Boolean deleted,
                                               @PathVariable int page,
                                               Model model) {
        Page<AccountRestrictionCode> codes = accountRestrictionCodeService
                .getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new AccountRestrictionCode());
        model.addAttribute("search", code);

        return "codes/accountRestriction";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewAccountRestrictionCode(@Valid AccountRestrictionCode accountRestrictionCode,
                                               BindingResult bindingResult,
                                               Model model) {
        if (accountRestrictionCodeService.getByCode(accountRestrictionCode.getCode()) != null) {
            bindingResult.addError(new ObjectError("accountRestrictionCode", "Ошибка: данный код уже существует"));
            log.warn("Ошибка при добавлении кода: данный код уже существует");
        }

        if (!bindingResult.hasErrors()) {
            accountRestrictionCodeService.save(accountRestrictionCode);

            log.info("Код добавлен");
            return "redirect:/codes/accountRestriction/0";
        }

        Page<AccountRestrictionCode> codes =
                accountRestrictionCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, accountRestrictionCode);

        return "codes/accountRestriction";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteAccountRestrictionCode(@PathVariable long id,
                                               @RequestParam String page) {
        accountRestrictionCodeService.deleteById(id);

        log.info("Код с id: " + id + " удален");
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateAccountRestrictionCode(@RequestParam long id,
                                               @RequestParam String newCode,
                                               @RequestParam String page) {
        if (accountRestrictionCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

        AccountRestrictionCode accountRestrictionCode = accountRestrictionCodeService.getById(id);
        accountRestrictionCode.setCode(newCode);
        accountRestrictionCodeService.save(accountRestrictionCode);

        log.info("Код с id: " + id + " редактирован");
        return "redirect:/codes/accountRestriction/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryAccountRestrictionCode(@PathVariable long id) {
        accountRestrictionCodeService.recoveryById(id);

        log.info("Код с id: " + id + " восстановлен");
        return "redirect:/codes/accountRestriction/0";
    }

    private void setModel(Model model,
                          Page<AccountRestrictionCode> codes,
                          AccountRestrictionCode accountRestrictionCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("accountRestrictionCode", accountRestrictionCode);
    }
}
