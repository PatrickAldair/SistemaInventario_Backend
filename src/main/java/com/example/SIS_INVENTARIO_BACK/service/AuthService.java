package com.example.SIS_INVENTARIO_BACK.service;

import com.example.SIS_INVENTARIO_BACK.dto.AuthResponse;
import com.example.SIS_INVENTARIO_BACK.dto.LoginRequest;
import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import com.example.SIS_INVENTARIO_BACK.repository.UsuarioRepository;
import com.example.SIS_INVENTARIO_BACK.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario user = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}