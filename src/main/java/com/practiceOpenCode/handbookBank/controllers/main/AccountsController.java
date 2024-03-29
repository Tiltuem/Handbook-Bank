package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.models.main.Accounts;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import com.practiceOpenCode.handbookBank.services.main.AccountsService;
import com.practiceOpenCode.handbookBank.services.main.BICDirectoryEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message-{messageId}/entry-{entryId}")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class AccountsController {
    private final AccountsService accountsService;
    private final BICDirectoryEntryService bicDirectoryEntryService;
    private final AbstractCodeService<RegulationAccountTypeCode> regulationAccountTypeCodeService;
    private final AbstractCodeService<AccountStatusCode> accountStatusCodeService;

    @GetMapping("/account-edit")
    public String getAccountById(@RequestParam long id,
                                 @PathVariable long messageId,
                                 @PathVariable long entryId,
                                 @RequestParam String page,
                                 Model model) {
        setModel(model, messageId, entryId, accountsService.getById(id), page);

        return "update/updateAccount";
    }

    @PostMapping("/account-delete/{id}")
    public String deleteAccount(@PathVariable long id, @RequestParam String page) {
        accountsService.deleteById(id);

        log.info("Аккаунт (id: " + id + ") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/account-recovery/{id}")
    public String recoveryAccount(@PathVariable long id, @RequestParam String page) {
        accountsService.recoveryById(id);

        log.info("Аккаунт (id: " + id + ") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-account")
    public String newAccount(@PathVariable long messageId,
                             @PathVariable long entryId,
                             @RequestParam String page,
                             Model model) {
        setModel(model, messageId, entryId, new Accounts(), page);

        return "add/addAccount";
    }

    @PostMapping("/account-add")
    public String addAccount(@Valid Accounts account,
                             BindingResult bindingResult,
                             @PathVariable long entryId,
                             @PathVariable long messageId,
                             @RequestParam String accountStatusCode,
                             @RequestParam String regulationAccountTypeCode,
                             @RequestParam String page,
                             Model model) {
        if (bindingResult.getErrorCount() == 2) {
            setCodes(account, regulationAccountTypeCode, accountStatusCode);
            bicDirectoryEntryService.updateById(entryId, account);

            log.info("Аккаунт добавлен");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, account, page);
        model.addAttribute("bindingResult", bindingResult);

        return "add/addAccount";
    }

    @PostMapping("/account-edit")
    public String updateAccount(@Valid Accounts account,
                                BindingResult bindingResult,
                                @PathVariable long entryId,
                                @PathVariable long messageId,
                                @RequestParam String accountStatusCode,
                                @RequestParam String regulationAccountTypeCode,
                                @RequestParam String page,
                                Model model) {
        setCodes(account, regulationAccountTypeCode, accountStatusCode);

        if (bindingResult.getErrorCount() == 2) {
            accountsService.update(account);

            log.info("Аккаунт (id: " + account.getId() + ") редактирован");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }

        setModel(model, messageId, entryId, account, page);
        model.addAttribute("bindingResult", bindingResult);

        return "update/updateAccount";
    }

    private void setModel(Model model, long messageId, long entryId, Accounts account, String page) {
        model.addAttribute("account", account);
        model.addAttribute("messageId", messageId);
        model.addAttribute("entryId", entryId);
        model.addAttribute("accountStatusCodeList", accountStatusCodeService.getAllCodeList());
        model.addAttribute("regulationAccountTypeCodeList", regulationAccountTypeCodeService.getAllCodeList());
        model.addAttribute("page", page);
    }

    private void setCodes(Accounts account, String regulationAccountTypeCode, String accountStatusCode) {
        account.setRegulationAccountTypeCode(regulationAccountTypeCodeService.getByCode(regulationAccountTypeCode));
        account.setAccountStatusCode(accountStatusCodeService.getByCode(accountStatusCode));
    }
}
