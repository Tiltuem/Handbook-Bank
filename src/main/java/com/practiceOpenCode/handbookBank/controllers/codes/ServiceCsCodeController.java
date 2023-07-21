package com.practiceOpenCode.handbookBank.controllers.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import com.practiceOpenCode.handbookBank.services.codes.ServiceCsCodeService;
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
@RequestMapping("/codes/serviceCs")
public class ServiceCsCodeController {
    @Autowired
    ServiceCsCodeService serviceCsCodeService;

    @GetMapping("/{page}")
    public String getAllServiceCsCode(@PathVariable int page, Model model) {
        Page<ServiceCsCode> codes = serviceCsCodeService.getAllCodes(PageRequest.of(page, 5, Sort.by("id")));
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
        return "codes/serviceCs";
    }

    @PostMapping("/add")
    public String addNewServiceCsCode(ServiceCsCode serviceCsCode) {
        serviceCsCodeService.save(serviceCsCode);
        return "redirect:/codes/serviceCs/0";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccountRestrictionCode(@PathVariable long id, @RequestParam String page) {
        serviceCsCodeService.deleteViaId(id);
        return "redirect:/codes/serviceCs/" + page;
    }

    @PostMapping("/update")
    public String updateAccountRestrictionCode(@RequestParam long id, @RequestParam String newCode, @RequestParam String page) {
        ServiceCsCode serviceCsCode = serviceCsCodeService.getViaId(id);
        serviceCsCode.setCode(newCode);
        serviceCsCodeService.save(serviceCsCode);
        return "redirect:/codes/serviceCs/" + page;
    }

}
