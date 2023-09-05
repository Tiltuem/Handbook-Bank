package com.practiceOpenCode.handbookBank.exception.handler;

import com.practiceOpenCode.handbookBank.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {DuplicateFileException.class, IncompatibleClassChangeError.class, NoSuchCodeException.class, NotFoundFileXmlException.class, UnmarshalXmlException.class})
    public String handleCustomException(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/customError";
    }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundPage(Model model) {
       model.addAttribute("message", "Страница не найдена");
        return "errors/customError";
   }
}