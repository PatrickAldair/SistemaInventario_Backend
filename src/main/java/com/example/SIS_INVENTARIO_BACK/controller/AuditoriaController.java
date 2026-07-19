package com.example.SIS_INVENTARIO_BACK.controller;

import com.example.SIS_INVENTARIO_BACK.model.MovimientoAuditoria;
import com.example.SIS_INVENTARIO_BACK.service.AuditoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    @Operation(summary = "Ver historial de movimientos (SOLO ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<MovimientoAuditoria>> obtenerHistorial() {
        return ResponseEntity.ok(auditoriaService.obtenerHistorial());
    }
}