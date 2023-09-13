package com.practiceOpenCode.handbookBank.services.security.impl;

import com.practiceOpenCode.handbookBank.dtos.JwtRequest;
import com.practiceOpenCode.handbookBank.dtos.JwtResponse;
import com.practiceOpenCode.handbookBank.dtos.RegistrationUserDto;
import com.practiceOpenCode.handbookBank.exception.UnauthorizedException;
import com.practiceOpenCode.handbookBank.services.security.AuthService;
import com.practiceOpenCode.handbookBank.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("invalid login/password");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ModelAndView createNewUser(RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword()))
            bindingResult.addError(new FieldError("registrationUserDto", "confirmPassword", "Ошибка: пароли не совпадают"));

        if(userService.findByUsername(registrationUserDto.getUsername()).isPresent())
            bindingResult.addError(new FieldError("registrationUserDto", "username", "Ошибка: пользователь с данным именем уже существует"));

        if (!bindingResult.hasErrors()) {
            userService.createNewUser(registrationUserDto);
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("authorization/signup");
        modelAndView.addObject("registrationUserDto", registrationUserDto);
        modelAndView.addObject("bindingResult", bindingResult);

        return modelAndView;
    }


}
