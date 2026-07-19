package com.example.SIS_INVENTARIO_BACK.service;

import com.example.SIS_INVENTARIO_BACK.dto.RegistroRequest;
import com.example.SIS_INVENTARIO_BACK.model.Role;
import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import com.example.SIS_INVENTARIO_BACK.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrarTrabajador(RegistroRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Error: El nombre de usuario ya está en uso.");
        }

        Usuario nuevoTrabajador = new Usuario();
        nuevoTrabajador.setUsername(request.getUsername());
        nuevoTrabajador.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevoTrabajador.setRole(Role.TRABAJADOR);

        return usuarioRepository.save(nuevoTrabajador);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}