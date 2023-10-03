package com.practiceOpenCode.handbookBank.controllers.main;

import com.practiceOpenCode.handbookBank.exceptions.NotFoundPageException;
import com.practiceOpenCode.handbookBank.models.main.FileInfo;
import com.practiceOpenCode.handbookBank.services.main.FileService;
import com.practiceOpenCode.handbookBank.services.main.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/import")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@Slf4j
public class ImportController {
    @Autowired
    MessageService messageService;
    @Autowired
    FileService fileService;

    @GetMapping("/{page}")
    public String getAllFiles(@PathVariable int page, Model model) {
        Page<FileInfo> files = fileService.getAllFiles(PageRequest.of(page, 5, Sort.by("id")));

        if (page > files.getTotalPages())
            throw new NotFoundPageException("Страница не найдена");

        model.addAttribute("files", files);
        model.addAttribute("maxPage", files.getTotalPages() - 1);

        return "mainPages/importFile";
    }

    @PostMapping("/date")
    public String saveMessageByDate(@RequestParam String date, @RequestParam String page) {
        messageService.save(date);

        log.info("ЭС добавлено");
        return "redirect:/import/" + page;
    }

    @PostMapping("/file")
    public String saveMessageByFile(@RequestParam("fileXml") MultipartFile fileXml, @RequestParam String page) {
        messageService.save(fileXml);

        log.info("ЭС добавлено");
        return "redirect:/import/" + page;
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable long id, @RequestParam String page) {
        fileService.deleteById(id);

        log.info("ЭС (id: " + id + ") удалено");
        return "redirect:/import/" + page;
    }

}
