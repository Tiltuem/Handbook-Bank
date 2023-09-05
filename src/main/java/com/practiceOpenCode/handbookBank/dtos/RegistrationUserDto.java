package com.practiceOpenCode.handbookBank.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationUserDto {
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String username;

    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Ошибка: поле не может быть пустым")
    @Email(message = "Ошибка: введите корректный Email")
    private String email;
}
