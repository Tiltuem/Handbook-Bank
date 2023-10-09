package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
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
@RequestMapping("/codes/regulationAccountType")
@Slf4j
public class RegulationAccountTypeCodeController {
    @Autowired
    AbstractCodeService<RegulationAccountTypeCode> regulationAccountTypeCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllRegulationAccountTypeCode(@RequestParam(required = false) String code,
                                                  @RequestParam(defaultValue = "false") Boolean deleted,
                                                  @PathVariable int page,
                                                  Model model) {
        Page<RegulationAccountTypeCode> codes = regulationAccountTypeCodeService
                .getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(new RegulationAccountTypeCode(), codes, model);
        model.addAttribute("search", code);

        return "codes/regulationAccountType";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewRegulationAccountTypeCode(@Valid RegulationAccountTypeCode regulationAccountType,
                                                  BindingResult bindingResult,
                                                  Model model) {
        if (regulationAccountTypeCodeService.getByCode(regulationAccountType.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("regulationAccountType", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            regulationAccountTypeCodeService.save(regulationAccountType);
            log.info("Код добавлен");

            return "redirect:/codes/regulationAccountType/0";
        }

        Page<RegulationAccountTypeCode> codes =
                regulationAccountTypeCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(regulationAccountType, codes, model);

        return "codes/regulationAccountType";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRegulationAccountTypeCode(@PathVariable long id, @RequestParam String page) {
        regulationAccountTypeCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/regulationAccountType/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateRegulationAccountTypeCode(@RequestParam long id,
                                                  @RequestParam String newCode,
                                                  @RequestParam String page) {
        if (regulationAccountTypeCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }


        RegulationAccountTypeCode regulationAccountType = regulationAccountTypeCodeService.getById(id);
        regulationAccountType.setCode(newCode);
        regulationAccountTypeCodeService.save(regulationAccountType);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/regulationAccountType/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryRegulationAccountTypeCode(@PathVariable long id) {
        regulationAccountTypeCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/regulationAccountType/0";
    }

    private void setModel(RegulationAccountTypeCode regulationAccountTypeCode,
                          Page<RegulationAccountTypeCode> codes,
                          Model model) {
        model.addAttribute("codes", codes);
        model.addAttribute("regulationAccountTypeCode", regulationAccountTypeCode);
    }
}
