package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
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
@RequestMapping("/codes/changeType")
@Slf4j
public class ChangeTypeCodeController {
    @Autowired
    AbstractCodeService<ChangeTypeCode> changeTypeCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllChangeTypeCode(@RequestParam(required = false) String code,
                                       @RequestParam(defaultValue = "false") Boolean deleted,
                                       @PathVariable int page,
                                       Model model) {
        Page<ChangeTypeCode> codes =
                changeTypeCodeService.getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new ChangeTypeCode());
        model.addAttribute("search", code);

        return "codes/changeType";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewChangeTypeCode(@Valid ChangeTypeCode changeTypeCode,
                                       BindingResult bindingResult,
                                       Model model) {
        if (changeTypeCodeService.getByCode(changeTypeCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("changeTypeCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            changeTypeCodeService.save(changeTypeCode);
            log.info("Код добавлен");

            return "redirect:/codes/changeType/0";
        }

        Page<ChangeTypeCode> codes =
                changeTypeCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
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
    public String updateChangeTypeCode(@RequestParam long id,
                                       @RequestParam String newCode,
                                       @RequestParam String page) {
        if (changeTypeCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

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
