package com.practiceOpenCode.handbookBank.controllers;

import com.practiceOpenCode.handbookBank.models.FileInfo;
import com.practiceOpenCode.handbookBank.services.FileService;
import com.practiceOpenCode.handbookBank.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/import")
public class ImportController {
    @Autowired
    MessageService messageService;
    @Autowired
    FileService fileService;

    @GetMapping("/{page}")
    public String getAllFiles(@PathVariable int page, Model model) {
        Page<FileInfo> files = fileService.getAllFiles(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("files", files);
        model.addAttribute("maxPage",  files.getTotalPages()-1);
        return "mainPages/importFile";
    }

    @PostMapping("/date")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String saveMessageByDate(@RequestParam String date, @RequestParam String page) {
        messageService.save(date);
        return "redirect:/import/" + page;
    }

    @PostMapping("/file")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String saveMessageByFile(@RequestParam("fileXml") MultipartFile fileXml, @RequestParam String page) {
        messageService.save(fileXml);
        return "redirect:/import/" + page;
    }

    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        fileService.deleteById(id);
        return "redirect:/import/" + page;
    }

}
