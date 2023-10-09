package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/codes/accountStatus")
@Slf4j
public class AccountStatusCodeController {
    @Autowired
    AbstractCodeService<AccountStatusCode> accountStatusCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllAccountStatusCode(@RequestParam(required = false) String code,
                                          @RequestParam(defaultValue = "false") Boolean deleted,
                                          @PathVariable int page,
                                          Model model) {
        Page<AccountStatusCode> codes =
                accountStatusCodeService.getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new AccountStatusCode());
        model.addAttribute("search", code);

        return "codes/accountStatus";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewAccountStatusCode(@Valid AccountStatusCode accountStatusCode,
                                          BindingResult bindingResult,
                                          Model model) {
        if (accountStatusCodeService.getByCode(accountStatusCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("accountStatusCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            accountStatusCodeService.save(accountStatusCode);

            log.info("Код добавлен");
            return "redirect:/codes/accountStatus/0";
        }

        Page<AccountStatusCode> codes =
                accountStatusCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, accountStatusCode);

        return "codes/accountStatus";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteAccountStatusCode(@PathVariable long id, @RequestParam String page) {
        accountStatusCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/accountStatus/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateAccountStatusCode(@RequestParam long id,
                                          @RequestParam String newCode,
                                          @RequestParam String page) {
        if (accountStatusCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }
        AccountStatusCode accountStatusCode = accountStatusCodeService.getById(id);
        accountStatusCode.setCode(newCode);
        accountStatusCodeService.save(accountStatusCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/accountStatus/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryAccountStatusCode(@PathVariable long id) {
        accountStatusCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/accountStatus/0";
    }

    private void setModel(Model model, Page<AccountStatusCode> codes, AccountStatusCode accountStatusCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("accountStatusCode", accountStatusCode);
    }
}
