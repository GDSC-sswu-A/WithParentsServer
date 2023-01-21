package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.request.GoogleLoginRequest;
import com.sswugdsc4a.withparents.dto.response.GoogleLoginResponse;
import com.sswugdsc4a.withparents.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

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

    @PostMapping("/api/auth/googleLogin")
    public GoogleLoginResponse googleLogin(
            @RequestBody GoogleLoginRequest body
    ) throws GeneralSecurityException, IOException {
        return authService.googleLogin(body.getIdToken());
    }

}
