package com.practiceOpenCode.handbookBank.services.security;

import com.practiceOpenCode.handbookBank.dtos.JwtRequest;
import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

@Service
public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequest authRequest);
    ModelAndView createNewUser(RegistrationUserDto registrationUserDto, BindingResult bindingResult);
}
