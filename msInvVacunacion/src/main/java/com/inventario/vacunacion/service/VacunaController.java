package com.inventario.vacunacion.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.inventario.vacunacion.repository.EmpleadoRepository;
import com.inventario.vacunacion.repository.VacunaRepository;
import com.inventario.vacunacion.entity.EmpleadoEntity;
import com.inventario.vacunacion.entity.VacunaEntity;
import com.inventario.vacunacion.utility.TipoVacuna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/vacuna")
public class VacunaController {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VacunaRepository vacunaRepository;

    @GetMapping("/empleado/consulta/{idEmpleado}")
    public ResponseEntity<List<VacunaEntity>> consultarVacuna(@PathVariable("idEmpleado") int empleadoId) {

        List<VacunaEntity> listaVacuna = vacunaRepository.findByEmpleadoId(empleadoId);

        if (listaVacuna.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaVacuna);
    }

    @PostMapping("/empleado/crear/{idEmpleado}")
    public ResponseEntity<VacunaEntity> crearVacuna(@Valid @PathVariable("idEmpleado") int empleadoId,
            @RequestBody VacunaEntity vacuna) {

        Optional<EmpleadoEntity> optionalEmpleado = empleadoRepository.findById(empleadoId);

        if (!optionalEmpleado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        vacuna.setEmpleado(optionalEmpleado.get());
        VacunaEntity newDosis = vacunaRepository.save(vacuna);
        return ResponseEntity.ok(newDosis);

    }

    @DeleteMapping("/empleado/{idEmpleado}/{id}")
    public ResponseEntity<Void> eliminarVacuna(@PathVariable("idEmpleado") int empleadoId, @PathVariable("id") int id) {

        Optional<EmpleadoEntity> optionalEmpleado = empleadoRepository.findById(empleadoId);

        if (!optionalEmpleado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        vacunaRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/consultarPorTipoVacuna/{tipoVacuna}")
    public ResponseEntity<Object> consultaPorTipoVacuna(@PathVariable("tipoVacuna") TipoVacuna tipoVacuna) {
        
        List<VacunaEntity> listaVacuna = vacunaRepository.findByTipo(tipoVacuna);
        
        if (listaVacuna.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(listaVacuna);
    }
}