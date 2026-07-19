package com.example.SIS_INVENTARIO_BACK.controller;

import com.example.SIS_INVENTARIO_BACK.dto.RegistroRequest;
import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import com.example.SIS_INVENTARIO_BACK.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/trabajadores")
    @Operation(summary = "Registrar un nuevo trabajador (SOLO ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Usuario> registrarTrabajador(@RequestBody RegistroRequest request) {
        return new ResponseEntity<>(usuarioService.registrarTrabajador(request), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios (SOLO ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}