package com.example.SIS_INVENTARIO_BACK.repository;

import com.example.SIS_INVENTARIO_BACK.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
