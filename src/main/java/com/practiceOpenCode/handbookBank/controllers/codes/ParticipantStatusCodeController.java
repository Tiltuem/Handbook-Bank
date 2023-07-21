package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.ParticipantStatusCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/codes/participantStatus")
public class ParticipantStatusCodeController {
    @Autowired
    ParticipantStatusCodeService participantStatusCodeService;

    @GetMapping("/{page}")
    public String getAllParticipantStatusCode(@PathVariable int page, Model model) {
        Page<ParticipantStatusCode> codes = participantStatusCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        if(page > 0) {
            model.addAttribute("prevPage", page-1);
        } else {
            model.addAttribute("prevPage", page);
        }

        if(page < codes.getTotalPages()-1) {
            model.addAttribute("nextPage", page+1);
        } else {
            model.addAttribute("nextPage", page);
        }
        return "codes/participantStatus";
    }

    @PostMapping("/add")
    public String addNewParticipantStatusCode(ParticipantStatusCode participantStatusCode) {
        participantStatusCodeService.save(participantStatusCode);
        return "redirect:/codes/participantStatus/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        participantStatusCodeService.deleteViaId(id);
        return "redirect:/codes/participantStatus/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ParticipantStatusCode participantStatusCode = participantStatusCodeService.getViaId(id);
        participantStatusCode.setCode(newCode);
        participantStatusCodeService.save(participantStatusCode);
        return "redirect:/codes/participantStatus/" + page;
    }
}
