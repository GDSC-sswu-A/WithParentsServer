package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/api/auth/testLogin")
    public String testLogin(
            @RequestParam String email,
            @RequestParam String pw
    ){
        return authService.testLogin(email, pw);
    }

    @GetMapping("/api/auth/test")
    public String test(
    ) {
        return "success";
    }
}
