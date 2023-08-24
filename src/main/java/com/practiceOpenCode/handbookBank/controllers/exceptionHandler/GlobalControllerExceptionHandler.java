package com.practiceOpenCode.handbookBank.controllers.exceptionHandler;

import com.practiceOpenCode.handbookBank.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {DuplicateFileException.class, IncompatibleClassChangeError.class, NoSuchCodeException.class, NotFoundFileXmlException.class, UnmarshalXmlException.class, NotFoundPageException.class})
    public String handleCustomException(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "exceptions/myException";
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public String handleCustomException(Model model) {
        model.addAttribute("message", "Ошибка: существующий объект");
        return "exceptions/myException";
    }
}