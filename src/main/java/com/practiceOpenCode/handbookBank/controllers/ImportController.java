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
    @Autowired
    FileService fileInfoService;

    @GetMapping("/{page}")
    public String getAllFiles(@PathVariable int page, Model model) {
        Page<FileInfo> files = fileInfoService.getAllFiles(PageRequest.of(page, 5, Sort.by("id")));
        model.addAttribute("files", files);
        model.addAttribute("maxPage",  files.getTotalPages()-1);
        return "mainPages/importFile";
    }
    @GetMapping("/{page}-sort")
    public String getAllSortedFiles(@PathVariable int page, Model model, @RequestParam String column, @RequestParam String direction) {
        Page<FileInfo> files;
        if(direction.equals("DESC")) {
            files = fileInfoService.getAllFiles(PageRequest.of(page, 5, Sort.by(column).descending()));

        } else {
            files = fileInfoService.getAllFiles(PageRequest.of(page, 5, Sort.by(column)));
        }
        model.addAttribute("files", files);
        model.addAttribute("maxPage",  files.getTotalPages()-1);
        return "mainPages/importFile";
    }

    @PostMapping("/date")
    public String saveMessageByDate(@RequestParam String date, @RequestParam String page) {
        messageService.save(date);
        return "redirect:/import/" + page;
    }

    @PostMapping("/file")
    public String saveMessageByFile(@RequestParam("fileXml") MultipartFile fileXml, @RequestParam String page) {
        messageService.save(fileXml);
        return "redirect:/import/" + page;
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        fileInfoService.deleteById(id);
        return "redirect:/import/" + page;
    }

    private Boolean checkFile(String name) {
        return fileService.getByName(name) != null;
    }
}
