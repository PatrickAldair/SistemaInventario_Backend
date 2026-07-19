package com.example.SIS_INVENTARIO_BACK.service;

import com.example.SIS_INVENTARIO_BACK.model.Producto;
import com.example.SIS_INVENTARIO_BACK.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final AuditoriaService auditoriaService;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        boolean esNuevo = (producto.getId() == null);
        Producto productoGuardado = productoRepository.save(producto);

        if (esNuevo) {
            auditoriaService.registrarMovimiento(
                    productoGuardado, "CREACION", null, productoGuardado.getUbicacion(),
                    "Se registró un nuevo lote de " + productoGuardado.getCantidad() + " unidades."
            );
        } else {
            auditoriaService.registrarMovimiento(
                    productoGuardado, "ACTUALIZACION", null, productoGuardado.getUbicacion(),
                    "Se actualizaron los datos del producto."
            );
        }

        return productoGuardado;
    }

    public void eliminarProducto(Long id) {
        Producto productoExistente = obtenerPorId(id);
        productoRepository.deleteById(id);

        auditoriaService.registrarMovimiento(
                productoExistente, "ELIMINACION", productoExistente.getUbicacion(), null,
                "El producto fue dado de baja del sistema."
        );
    }

    public Producto moverProducto(Long id, String nuevaUbicacion) {
        Producto producto = obtenerPorId(id);
        String zonaOrigen = producto.getUbicacion();

        producto.setUbicacion(nuevaUbicacion);
        Producto productoActualizado = productoRepository.save(producto);

        auditoriaService.registrarMovimiento(
                productoActualizado,
                "TRASLADO",
                zonaOrigen,
                nuevaUbicacion,
                "Se trasladó el material desde " + zonaOrigen + " hacia " + nuevaUbicacion
        );

        return productoActualizado;
    }

    public Producto corregirStock(Long id, Integer nuevaCantidad) {
        Producto producto = obtenerPorId(id);
        Integer cantidadAnterior = producto.getCantidad();

        producto.setCantidad(nuevaCantidad);
        Producto productoActualizado = productoRepository.save(producto);

        auditoriaService.registrarMovimiento(
                productoActualizado,
                "ACTUALIZACION",
                productoActualizado.getUbicacion(),
                productoActualizado.getUbicacion(),
                "El Administrador corrigió el stock manual de " + cantidadAnterior + " a " + nuevaCantidad + " unidades."
        );

        return productoActualizado;
    }
}