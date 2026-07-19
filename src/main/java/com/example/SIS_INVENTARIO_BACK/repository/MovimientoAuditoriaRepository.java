package com.example.SIS_INVENTARIO_BACK.repository;

import com.example.SIS_INVENTARIO_BACK.model.MovimientoAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimientoAuditoriaRepository extends JpaRepository<MovimientoAuditoria, Long> {
    List<MovimientoAuditoria> findAllByOrderByFechaMovimientoDesc();
}
