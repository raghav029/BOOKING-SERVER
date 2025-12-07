package com.rtt.luxurycar.auth.controller;

import com.rtt.luxurycar.auth.dto.AuthRequestDto;
import com.rtt.luxurycar.auth.dto.AuthResponseDto;
import com.rtt.luxurycar.auth.dto.SignupRequestDto;
import com.rtt.luxurycar.auth.service.AuthService;
import com.rtt.luxurycar.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequestDto dto) {
        authService.signup(dto);
        return ApiResponse.ok(null);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto dto) {
        return ApiResponse.ok(authService.login(dto));
    }
}
