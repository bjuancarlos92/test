package com.inventario.vacunacion.repository;

import java.util.List;

import com.inventario.vacunacion.entity.EmpleadoEntity;
import com.inventario.vacunacion.utility.EstadoVacunacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {
	 EmpleadoEntity findByCedula(String cedula);
	 List<EmpleadoEntity> findByEstadoVacunacion(EstadoVacunacion estadoVacunacion);
}