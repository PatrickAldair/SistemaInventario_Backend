package com.example.SIS_INVENTARIO_BACK.controller;

import com.example.SIS_INVENTARIO_BACK.dto.AuthResponse;
import com.example.SIS_INVENTARIO_BACK.dto.LoginRequest;
import com.example.SIS_INVENTARIO_BACK.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}