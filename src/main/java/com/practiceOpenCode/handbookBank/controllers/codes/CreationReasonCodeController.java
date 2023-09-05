package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exception.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
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
@RequestMapping("/codes/creationReason")
@Slf4j
public class CreationReasonCodeController {
    @Autowired
    AbstractCodeService<CreationReasonCode> creationReasonCodeService;

    @GetMapping("/{page}")
    public String getAllCreationReasonCode(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted, @PathVariable int page, final Model model) {
        Page<CreationReasonCode> codes = creationReasonCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);
        if (codes.isEmpty() && Objects.isNull(code))
            throw new NotFoundPageException("Страница не найдена");

        setModel(model, codes, new CreationReasonCode());
        model.addAttribute("search", code);
        return "codes/creationReason";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewCreationReasonCode(@Valid CreationReasonCode creationReasonCode, BindingResult bindingResult, Model model) {
        if (creationReasonCodeService.getByCode(creationReasonCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("creationReasonCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            creationReasonCode.setDeleted(false);
            log.info("Код добавлен");
            creationReasonCodeService.save(creationReasonCode);

            return "redirect:/codes/creationReason/0";
        }

        Page<CreationReasonCode> codes = creationReasonCodeService.getAllCodes(PageRequest.of(0, 5, Sort.by("id")), null, null);
        // model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, creationReasonCode);

        return "codes/creationReason";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCreationReasonCode(@PathVariable long id, @RequestParam String page) {
        creationReasonCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/creationReason/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateCreationReasonCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        CreationReasonCode creationReasonCode = creationReasonCodeService.getById(id);
        creationReasonCode.setCode(newCode);
        creationReasonCodeService.save(creationReasonCode);
        log.info("Код (id: " + id + ") редактирован");

        return "redirect:/codes/creationReason/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryCreationReasonCode(@PathVariable long id) {
        creationReasonCodeService.recoveryById(id);
        log.info("Код (id: " + id + ") восстановлен");

        return "redirect:/codes/creationReason/0";
    }

    private void setModel(Model model, Page<CreationReasonCode> codes, CreationReasonCode creationReasonCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("creationReasonCode", creationReasonCode);
    }
}
