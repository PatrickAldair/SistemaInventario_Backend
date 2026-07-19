package com.example.SIS_INVENTARIO_BACK.service;

import com.example.SIS_INVENTARIO_BACK.model.MovimientoAuditoria;
import com.example.SIS_INVENTARIO_BACK.model.Producto;
import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import com.example.SIS_INVENTARIO_BACK.repository.MovimientoAuditoriaRepository;
import com.example.SIS_INVENTARIO_BACK.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final MovimientoAuditoriaRepository auditoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public void registrarMovimiento(Producto producto, String accion, String zonaOrigen, String zonaDestino, String detalles) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MovimientoAuditoria movimiento = new MovimientoAuditoria();
        movimiento.setUsuario(usuario);
        movimiento.setProducto(producto);
        movimiento.setAccion(accion);
        movimiento.setZonaOrigen(zonaOrigen);
        movimiento.setZonaDestino(zonaDestino);
        movimiento.setFechaMovimiento(LocalDateTime.now());
        movimiento.setDetalles(detalles);

        auditoriaRepository.save(movimiento);
    }

    public List<MovimientoAuditoria> obtenerHistorial() {
        return auditoriaRepository.findAllByOrderByFechaMovimientoDesc();
    }
}