package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
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
@RequestMapping("/codes/serviceCs")
@RequiredArgsConstructor
@Slf4j
public class ServiceCsCodeController {
    private final AbstractCodeService<ServiceCsCode> serviceCsCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllServiceCsCode(@RequestParam(required = false) String code,
                                      @RequestParam(defaultValue = "false") Boolean deleted,
                                      @PathVariable int page,
                                      Model model) {
        Page<ServiceCsCode> codes =
                serviceCsCodeService.getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new ServiceCsCode());
        model.addAttribute("search", code);

        return "codes/serviceCs";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewServiceCsCode(@Valid ServiceCsCode serviceCsCode,
                                      BindingResult bindingResult,
                                      Model model) {
        if (serviceCsCodeService.getByCode(serviceCsCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("serviceCsCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            serviceCsCodeService.save(serviceCsCode);
            log.info("Код добавлен");

            return "redirect:/codes/serviceCs/0";
        }

        Page<ServiceCsCode> codes =
                serviceCsCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, serviceCsCode);

        return "codes/serviceCs";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteServiceCsCode(@PathVariable long id, @RequestParam String page) {
        serviceCsCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/serviceCs/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateServiceCsCode(@RequestParam long id,
                                      @RequestParam String newCode,
                                      @RequestParam String page) {
        if (serviceCsCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

        ServiceCsCode serviceCsCode = serviceCsCodeService.getById(id);
        serviceCsCode.setCode(newCode);
        serviceCsCodeService.save(serviceCsCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/serviceCs/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryServiceCsCode(@PathVariable long id) {
        serviceCsCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/serviceCs/0";
    }

    private void setModel(Model model, Page<ServiceCsCode> codes, ServiceCsCode serviceCsCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("serviceCsCode", serviceCsCode);
    }
}
