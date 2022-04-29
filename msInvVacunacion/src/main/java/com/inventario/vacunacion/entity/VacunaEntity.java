package com.inventario.vacunacion.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventario.vacunacion.utility.TipoVacuna;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vacuna")
public class VacunaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoVacuna tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoVacuna getTipo() {
        return tipo;
    }

    public void setTipo(TipoVacuna tipo) {
        this.tipo = tipo;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    @Column(name = "dosis", nullable = false)
    @NotNull(message = "La cantidad de dosis es obligatorio")
    private int dosis;


    @Column(name = "fecha", nullable = false)
    @NotNull(message = "La fecha de vacunacion es obligatorio")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-5")
    private Date fecha;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private EmpleadoEntity empleado;
}