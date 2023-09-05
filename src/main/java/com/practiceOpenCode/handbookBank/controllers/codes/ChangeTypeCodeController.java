package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
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
@RequestMapping("/codes/changeType")
@Slf4j
public class ChangeTypeCodeController {
    @Autowired
    AbstractCodeService<ChangeTypeCode> changeTypeCodeService;

    @GetMapping("/{page}")
    public String getAllChangeTypeCode(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted, @PathVariable int page, final Model model) {
        Page<ChangeTypeCode> codes = changeTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);
        if (codes.isEmpty() && Objects.isNull(code))
            throw new NotFoundPageException("Страница не найдена");

        setModel(model, codes, new ChangeTypeCode());
        model.addAttribute("search", code);
        return "codes/changeType";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewChangeTypeCode(@Valid ChangeTypeCode changeTypeCode, BindingResult bindingResult, Model model) {
        if (changeTypeCodeService.getByCode(changeTypeCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("changeTypeCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            changeTypeCode.setDeleted(false);
            log.info("Код добавлен");
            changeTypeCodeService.save(changeTypeCode);

            return "redirect:/codes/changeType/0";
        }

        Page<ChangeTypeCode> codes = changeTypeCodeService.getAllCodes(PageRequest.of(0, 5, Sort.by("id")), null, null);
        // model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, changeTypeCode);

        return "codes/changeType";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteChangeTypeCode(@PathVariable long id, @RequestParam String page) {
        changeTypeCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/changeType/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateChangeTypeCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ChangeTypeCode changeTypeCode = changeTypeCodeService.getById(id);
        changeTypeCode.setCode(newCode);
        changeTypeCodeService.save(changeTypeCode);
        log.info("Код (id: " + id + ") редактирован");

        return "redirect:/codes/changeType/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryChangeTypeCode(@PathVariable long id) {
        changeTypeCodeService.recoveryById(id);
        log.info("Код (id: " + id + ") восстановлен");

        return "redirect:/codes/changeType/0";
    }

    private void setModel(Model model, Page<ChangeTypeCode> codes, ChangeTypeCode changeTypeCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("changeTypeCode", changeTypeCode);
    }
}
