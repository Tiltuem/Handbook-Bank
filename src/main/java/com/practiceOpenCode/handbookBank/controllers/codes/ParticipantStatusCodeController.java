package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
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
@RequestMapping("/codes/participantStatus")
@RequiredArgsConstructor
@Slf4j
public class ParticipantStatusCodeController {
    private final AbstractCodeService<ParticipantStatusCode> participantStatusCodeService;
    private static final int SIZE_PAGE = 5;

    @GetMapping("/{page}")
    public String getAllParticipantStatusCode(@RequestParam(required = false) String code,
                                              @RequestParam(defaultValue = "false") Boolean deleted,
                                              @PathVariable int page,
                                              Model model) {
        Page<ParticipantStatusCode> codes = participantStatusCodeService
                .getAllCodes(PageRequest.of(page, SIZE_PAGE, Sort.by("id")), code, deleted);

        if (codes.isEmpty() && Objects.isNull(code)) {
            throw new NotFoundPageException("Страница не найдена");
        }

        setModel(model, codes, new ParticipantStatusCode());
        model.addAttribute("search", code);

        return "codes/participantStatus";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewParticipantStatusCode(@Valid ParticipantStatusCode participantStatusCode,
                                              BindingResult bindingResult,
                                              Model model) {
        if (participantStatusCodeService.getByCode(participantStatusCode.getCode()) != null) {
            log.warn("Ошибка при добавлении кода: данный код уже существует");
            bindingResult.addError(new ObjectError("participantStatusCode", "Ошибка: данный код уже существует"));
        }

        if (!bindingResult.hasErrors()) {
            participantStatusCodeService.save(participantStatusCode);
            log.info("Код добавлен");

            return "redirect:/codes/participantStatus/0";
        }

        Page<ParticipantStatusCode> codes =
                participantStatusCodeService.getAllCodes(PageRequest.of(0, SIZE_PAGE, Sort.by("id")), null, null);

        model.addAttribute("page", 0);
        model.addAttribute("bindingResult", bindingResult);
        setModel(model, codes, participantStatusCode);

        return "codes/participantStatus";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteParticipantStatusCode(@PathVariable long id, @RequestParam String page) {
        participantStatusCodeService.deleteById(id);
        log.info("Код (id: " + id + ") удален");
        return "redirect:/codes/participantStatus/" + page;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateParticipantStatusCode(@RequestParam long id,
                                              @RequestParam String newCode,
                                              @RequestParam String page) {
        if (participantStatusCodeService.getByCode(newCode) != null) {
            throw new DuplicateFileException("Ошибка: данный код уже существует");
        }

        ParticipantStatusCode participantStatusCode = participantStatusCodeService.getById(id);
        participantStatusCode.setCode(newCode);
        participantStatusCodeService.save(participantStatusCode);

        log.info("Код (id: " + id + ") редактирован");
        return "redirect:/codes/participantStatus/" + page;
    }

    @PostMapping("/recovery/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String recoveryParticipantStatusCode(@PathVariable long id) {
        participantStatusCodeService.recoveryById(id);

        log.info("Код (id: " + id + ") восстановлен");
        return "redirect:/codes/participantStatus/0";
    }

    private void setModel(Model model, Page<ParticipantStatusCode> codes, ParticipantStatusCode participantStatusCode) {
        model.addAttribute("codes", codes);
        model.addAttribute("participantStatusCode", participantStatusCode);
    }
}
