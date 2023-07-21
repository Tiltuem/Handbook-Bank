package com.practiceOpenCode.handbookBank.controllers.codes;


import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.ParticipantTypeCodeService;
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
@RequestMapping("/codes/participantType")
public class ParticipantTypeCodeController {
    @Autowired
    ParticipantTypeCodeService participantTypeCodeService;

    @GetMapping("/{page}")
    public String getAllParticipantTypeCode(@PathVariable int page, Model model) {
        Page<ParticipantTypeCode> codes = participantTypeCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
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

        return "codes/participantType";
    }

    @PostMapping("/add")
    public String addNewParticipantTypeCode(ParticipantTypeCode participantTypeCode) {
        participantTypeCodeService.save(participantTypeCode);
        return "redirect:/codes/participantType/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        participantTypeCodeService.deleteViaId(id);
        return "redirect:/codes/participantType/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ParticipantTypeCode participantTypeCode = participantTypeCodeService.getViaId(id);
        participantTypeCode.setCode(newCode);
        participantTypeCodeService.save(participantTypeCode);
        return "redirect:/codes/participantType/" + page;
    }
}
