package com.inventario.vacunacion.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.inventario.vacunacion.utility.EstadoVacunacion;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cedula", nullable = false, length = 10, unique = true)
    @Pattern(regexp = "[0-9]{10,10}", message = "La cedula debe ser de 10 digitos")
    @NotBlank(message = "La cedula es obligatoria")
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "Los nombres son obligatorios")
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "El nombre solo puede contener caracteres de tipo letra")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @NotBlank(message = "Los apellidos son obligatorios")
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "El apellido solo puede contener caracteres de tipo letra")
    private String apellido;

    @Column(name = "correo", nullable = false, length = 50)
    @Email(message = "El campo email no tiene un formato valido")
    @NotBlank(message = "El correo electronico es obligatorio")
    private String correo;

    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "celular")
    private String celular;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "clave")
    private String clave;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estadoVacunacion")
    @Enumerated(value = EnumType.STRING)
    private EstadoVacunacion estadoVacunacion;

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public EstadoVacunacion getEstadoVacunacion() {
        return estadoVacunacion;
    }

    public void setEstadoVacunacion(EstadoVacunacion estadoVacunacion) {
        this.estadoVacunacion = estadoVacunacion;
    }

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empleado")
    private Set<VacunaEntity> vacuna = new HashSet<>();
    
    public EmpleadoEntity() {
        
    }
    
    public EmpleadoEntity(int id) {
       // super();
     //   this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<VacunaEntity> getVacuna() {
        return vacuna;
    }

    public void setVacuna(Set<VacunaEntity> vacuna) {
        this.vacuna = vacuna;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
