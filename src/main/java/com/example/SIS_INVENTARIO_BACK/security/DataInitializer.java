package com.example.SIS_INVENTARIO_BACK.security;

import com.example.SIS_INVENTARIO_BACK.model.Role;
import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import com.example.SIS_INVENTARIO_BACK.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            usuarioRepository.save(admin);
            System.out.println(" Usuario ADMIN creado por defecto. Credenciales -> admin : admin123");
        }
    }
}