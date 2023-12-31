package com.practiceOpenCode.handbookBank.services.security;

import com.practiceOpenCode.handbookBank.dtos.JwtRequest;
import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequest authRequest);

    ModelAndView createNewUser(RegistrationUserDto registrationUserDto, BindingResult bindingResult);
}
