package com.example.SIS_INVENTARIO_BACK.controller;

import com.example.SIS_INVENTARIO_BACK.model.Producto;
import com.example.SIS_INVENTARIO_BACK.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por su ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mover")
    @Operation(summary = "Trasladar un producto de zona", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Producto> moverProducto(@PathVariable Long id, @RequestParam String nuevaUbicacion) {
        return ResponseEntity.ok(productoService.moverProducto(id, nuevaUbicacion));
    }

    @PutMapping("/{id}/stock")
    @Operation(summary = "Corregir el stock de un producto (SOLO ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Producto> corregirStock(@PathVariable Long id, @RequestParam Integer nuevaCantidad) {
        return ResponseEntity.ok(productoService.corregirStock(id, nuevaCantidad));
    }
}