package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.services.AccountsService;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message-{messageId}/entry-{entryId}")
@RequiredArgsConstructor
@Controller
@Slf4j
public class AccountsController {
    @Autowired
    AccountsService accountsService;
    @Autowired
    BICDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    AbstractCodeService<RegulationAccountTypeCode> regulationAccountTypeCodeService;
    @Autowired
    AbstractCodeService<AccountStatusCode> accountStatusCodeService;

    @GetMapping("/account-edit")
    ////@PreAuthorize("hasAuthority('ROLE_USER')")
    public String getAccountById(@RequestParam long id, Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, accountsService.getById(id), page);
        return "update/updateAccount";
    }

    @PostMapping("/account-delete/{id}")
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteAccount(@PathVariable long id, @RequestParam String page) {
        accountsService.deleteById(id);
        log.debug("Аккаунт (id: " + id +") удалён");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @PostMapping("/account-recovery/{id}")
    ////@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryAccount(@PathVariable long id, @RequestParam String page) {
        accountsService.recoveryById(id);
        log.debug("Аккаунт (id: " + id +") восстановлен");
        return "redirect:/message-{messageId}/directory-entry/" + page;
    }

    @GetMapping("/new-account")
    ////@PreAuthorize("hasAuthority('ROLE_USER')")
    public String newAccount(Model model, @PathVariable long messageId, @PathVariable long entryId, @RequestParam String page) {
        setModel(model, messageId, entryId, new Accounts(), page);
        return "add/addAccount";
    }

    @PostMapping("/account-add")
    ////@PreAuthorize("hasAuthority('ROLE_USER')")
    public String addAccount(@Valid Accounts account, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String accountStatusCode, @RequestParam String regulationAccountTypeCode, @RequestParam String page) {
        if (bindingResult.getErrorCount() == 2) {
            setCodes(account, regulationAccountTypeCode, accountStatusCode);
            bicDirectoryEntryService.updateById(entryId, account);
            log.debug("Аккаунт добавлен");
            return "redirect:/message-{messageId}/directory-entry/" + page;
        }
        setModel(model, messageId, entryId, account, page);
        model.addAttribute("bindingResult", bindingResult);
        return "add/addAccount";
    }

    @PostMapping("/account-edit")
    ////@PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateAccount(@Valid Accounts account, BindingResult bindingResult, Model model, @PathVariable long entryId, @PathVariable long messageId, @RequestParam String accountStatusCode, @RequestParam String regulationAccountTypeCode, @RequestParam String page) {
        setCodes(account, regulationAccountTypeCode, accountStatusCode);
        if (bindingResult.getErrorCount() == 2) {
            accountsService.update(account);
            log.debug("Аккаунт (id: " + account.getId() +") редактирован");
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
