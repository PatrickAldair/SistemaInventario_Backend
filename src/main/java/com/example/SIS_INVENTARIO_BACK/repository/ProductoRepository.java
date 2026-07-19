package com.example.SIS_INVENTARIO_BACK.repository;

import com.example.SIS_INVENTARIO_BACK.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}