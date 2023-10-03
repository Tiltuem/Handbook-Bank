package com.practiceOpenCode.handbookBank.controllers.codes;


import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
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
@RequestMapping("/codes/participantType")
@Slf4j
public class ParticipantTypeCodeController {
    @Autowired
    AbstractCodeService<ParticipantTypeCode> participantTypeCodeService;

    @GetMapping("/{page}")
    public String getAllParticipantTypeCode(@RequestParam(name = "code", required = false) String code,
                                            @RequestParam(name = "deleted", defaultValue = "false") Boolean showDeleted,
                                            @PathVariable int page,
                                            Model model) {
        Page<ParticipantTypeCode> codes =
                participantTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")), code, showDeleted);

        if (codes.isEmpty() && Objects.isNull(code))
            throw new NotFoundPageException("Страница не найдена");

        setModel(model, codes, new ParticipantTypeCode());
        model.addAttribute("search", code);

        return "codes/participantType";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewParticipantTypeCode(@Valid ParticipantTypeCode participantTypeCode,
                                            BindingResult bindingResult,
                                            Model model) {
        if (participantTypeCodeService.getByCode(participantTypeCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("participantTypeCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            participantTypeCodeService.save(participantTypeCode);
            log.info("Код добавлен");
            return "redirect:/codes/participantType/0";
        }

        Page<ParticipantTypeCode> codes =
                participantTypeCodeService.getAllCodes(PageRequest.of(0, 5, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, participantTypeCode);

        return "codes/participantType";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteParticipantTypeCode(@PathVariable long id, @RequestParam String page) {
        participantTypeCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");

        return "redirect:/codes/participantType/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateParticipantTypeCode(@RequestParam long id,
                                            @RequestParam String newCode,
                                            @RequestParam String page) {
        if (participantTypeCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует") ;
        }

        ParticipantTypeCode participantTypeCode = participantTypeCodeService.getById(id);
        participantTypeCode.setCode(newCode);
        participantTypeCodeService.save(participantTypeCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/participantType/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryParticipantTypeCode(@PathVariable long id) {
        participantTypeCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/participantType/0";
    }

    private void setModel(Model model, Page<ParticipantTypeCode> codes, ParticipantTypeCode participantTypeCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("participantTypeCode", participantTypeCode);
    }
}
