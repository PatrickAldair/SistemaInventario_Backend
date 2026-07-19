package com.example.SIS_INVENTARIO_BACK.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String lote;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String ubicacion;
}
