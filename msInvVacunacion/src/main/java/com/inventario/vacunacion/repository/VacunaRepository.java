package com.inventario.vacunacion.repository;

import java.util.List;

import com.inventario.vacunacion.entity.VacunaEntity;
import com.inventario.vacunacion.utility.TipoVacuna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<VacunaEntity, Integer> {
    
    List<VacunaEntity> findByEmpleadoId(int empleado);
    List<VacunaEntity> findByTipo(TipoVacuna tipo);
}