package com.practiceOpenCode.handbookBank.controllers.codes;


import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
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
@RequestMapping("/codes/restriction")
@RequiredArgsConstructor
@Slf4j
public class RestrictionCodeController {
    private final AbstractCodeService<RestrictionCode> restrictionCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllRestrictionCode(@RequestParam(required = false) String code,
                                        @RequestParam(defaultValue = "false") Boolean deleted,
                                        @PathVariable int page,
                                        Model model) {
        Page<RestrictionCode> codes =
                restrictionCodeService.getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new RestrictionCode());
        model.addAttribute("search", code);

        return "codes/restriction";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewRestrictionCode(@Valid RestrictionCode restrictionCode,
                                        BindingResult bindingResult,
                                        Model model) {
        if (restrictionCodeService.getByCode(restrictionCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("restrictionCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            restrictionCodeService.save(restrictionCode);
            log.info("Код добавлен");

            return "redirect:/codes/restriction/0";
        }

        Page<RestrictionCode> codes =
                restrictionCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, restrictionCode);

        return "codes/restriction";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRestrictionCode(@PathVariable long id, @RequestParam String page) {
        restrictionCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/restriction/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateRestrictionCode(@RequestParam long id,
                                        @RequestParam String newCode,
                                        @RequestParam String page) {
        if (restrictionCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

        RestrictionCode restrictionCode = restrictionCodeService.getById(id);
        restrictionCode.setCode(newCode);
        restrictionCodeService.save(restrictionCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/restriction/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryRestrictionCode(@PathVariable long id) {
        restrictionCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/restriction/0";
    }

    private void setModel(Model model, Page<RestrictionCode> codes, RestrictionCode restrictionCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("restrictionCode", restrictionCode);
    }
}
