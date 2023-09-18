package com.practiceOpenCode.handbookBank.exceptions.handler;

import com.practiceOpenCode.handbookBank.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
            DuplicateFileException.class,
            IncompatibleClassChangeError.class,
            NoSuchCodeException.class,
            NotFoundFileXmlException.class,
            UnmarshalXmlException.class,
            UnauthorizedException.class})
    public String handleCustomException(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/customError";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundPageException.class)
    public String handleNotFoundPage(Model model) {
        model.addAttribute("message", "Страница не найдена");
        return "errors/customError";
    }
}