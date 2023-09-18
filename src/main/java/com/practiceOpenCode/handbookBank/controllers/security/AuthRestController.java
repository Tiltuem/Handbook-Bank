package com.practiceOpenCode.handbookBank.controllers.security;

import com.practiceOpenCode.handbookBank.dtos.JwtRequest;
import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import com.practiceOpenCode.handbookBank.services.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        Map<Object, Object> response = new HashMap<>();
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/signup")
    public ModelAndView createNewUser(@Valid RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
        return authService.createNewUser(registrationUserDto, bindingResult);
    }
}