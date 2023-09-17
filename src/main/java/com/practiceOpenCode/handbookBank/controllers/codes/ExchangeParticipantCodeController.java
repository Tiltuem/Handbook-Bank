package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/codes/exchangeParticipant")
@Slf4j
public class ExchangeParticipantCodeController {
    @Autowired
    AbstractCodeService<ExchangeParticipantCode> exchangeParticipantCodeService;

    @GetMapping("/{page}")
    public String getAllExchangeParticipantCode(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted, @PathVariable int page, final Model model) {
        Page<ExchangeParticipantCode> codes = exchangeParticipantCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);
        if (codes.isEmpty() && Objects.isNull(code))
            throw new NotFoundPageException("Страница не найдена");

        setModel(model, codes, new ExchangeParticipantCode());
        model.addAttribute("search", code);
        return "codes/exchangeParticipant";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewExchangeParticipantCode(@Valid ExchangeParticipantCode exchangeParticipantCode, BindingResult bindingResult, Model model) {
        if (exchangeParticipantCodeService.getByCode(exchangeParticipantCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("exchangeParticipantCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            exchangeParticipantCode.setDeleted(false);
            log.info("Код добавлен");
            exchangeParticipantCodeService.save(exchangeParticipantCode);

            return "redirect:/codes/exchangeParticipant/0";
        }

        Page<ExchangeParticipantCode> codes = exchangeParticipantCodeService.getAllCodes(PageRequest.of(0, 5, Sort.by("id")), null, null);
        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, exchangeParticipantCode);

        return "codes/exchangeParticipant";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteExchangeParticipantCode(@PathVariable long id, @RequestParam String page) {
        exchangeParticipantCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/exchangeParticipant/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateExchangeParticipantCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        if (exchangeParticipantCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует") ;
        }
        ExchangeParticipantCode exchangeParticipantCode = exchangeParticipantCodeService.getById(id);
        exchangeParticipantCode.setCode(newCode);
        exchangeParticipantCodeService.save(exchangeParticipantCode);
        log.info("Код (id: " + id + ") редактирован");

        return "redirect:/codes/exchangeParticipant/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryExchangeParticipantCode(@PathVariable long id) {
        exchangeParticipantCodeService.recoveryById(id);
        log.info("Код (id: " + id + ") восстановлен");

        return "redirect:/codes/exchangeParticipant/0";
    }

    private void setModel(Model model, Page<ExchangeParticipantCode> codes, ExchangeParticipantCode exchangeParticipantCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("exchangeParticipantCode", exchangeParticipantCode);
    }
}
