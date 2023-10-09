package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
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
@RequestMapping("/codes/informationType")
@Slf4j
public class InformationTypeCodeController {
    @Autowired
    AbstractCodeService<InformationTypeCode> informationTypeCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllAInformationTypeCode(@RequestParam(required = false) String code,
                                             @RequestParam(defaultValue = "false") Boolean deleted,
                                             @PathVariable int page,
                                             Model model) {
        Page<InformationTypeCode> codes = informationTypeCodeService
                .getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new InformationTypeCode());
        model.addAttribute("search", code);

        return "codes/informationType";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewInformationTypeCode(@Valid InformationTypeCode informationTypeCode,
                                            BindingResult bindingResult,
                                            Model model) {
        if (informationTypeCodeService.getByCode(informationTypeCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("informationTypeCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            informationTypeCodeService.save(informationTypeCode);
            log.info("Код добавлен");

            return "redirect:/codes/informationType/0";
        }

        Page<InformationTypeCode> codes =
                informationTypeCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, informationTypeCode);

        return "codes/informationType";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteInformationTypeCode(@PathVariable long id, @RequestParam String page) {
        informationTypeCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");
        return "redirect:/codes/informationType/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateInformationTypeCode(@RequestParam long id,
                                            @RequestParam String newCode,
                                            @RequestParam String page) {
        if (informationTypeCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

        InformationTypeCode informationTypeCode = informationTypeCodeService.getById(id);
        informationTypeCode.setCode(newCode);
        informationTypeCodeService.save(informationTypeCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/informationType/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryInformationTypeCode(@PathVariable long id) {
        informationTypeCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/informationType/0";
    }

    private void setModel(Model model, Page<InformationTypeCode> codes, InformationTypeCode informationTypeCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("informationTypeCode", informationTypeCode);
    }
}
