package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.services.codes.ExchangeParticipantCodeService;
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
@RequestMapping("/codes/exchangeParticipant")
public class ExchangeParticipantCodeController {
    @Autowired
    ExchangeParticipantCodeService exchangeParticipantCodeService;

    @GetMapping("/{page}")
    public String getAllExchangeParticipantCode(@PathVariable int page, Model model) {
        Page<ExchangeParticipantCode> codes = exchangeParticipantCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("codes", codes);
        model.addAttribute("maxPage",  codes.getTotalPages()-1);
        return "codes/exchangeParticipant";
    }

    @PostMapping("/add")
    public String addNewExchangeParticipantCode(ExchangeParticipantCode exchangeParticipantCode) {
        exchangeParticipantCodeService.save(exchangeParticipantCode);
        return "redirect:/codes/exchangeParticipant/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        exchangeParticipantCodeService.deleteViaId(id);
        return "redirect:/codes/exchangeParticipant/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ExchangeParticipantCode exchangeParticipantCode = exchangeParticipantCodeService.getViaId(id);
        exchangeParticipantCode.setCode(newCode);
        exchangeParticipantCodeService.save(exchangeParticipantCode);
        return "redirect:/codes/exchangeParticipant/" + page;
    }
}
