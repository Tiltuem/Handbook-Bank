package com.practiceOpenCode.handbookBank.services.security;

import com.practiceOpenCode.handbookBank.dtos.JwtRequest;
import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequest authRequest);
    ModelAndView createNewUser(RegistrationUserDto registrationUserDto, BindingResult bindingResult);
}
