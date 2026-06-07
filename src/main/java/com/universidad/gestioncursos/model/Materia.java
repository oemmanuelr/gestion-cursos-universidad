package com.universidad.gestioncursos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String codigoMateria;

    private Integer creditos;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    public Materia() {}

    public Materia(String nombre, String codigoMateria, Integer creditos, Carrera carrera) {
        this.nombre = nombre;
        this.codigoMateria = codigoMateria;
        this.creditos = creditos;
        this.carrera = carrera;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigoMateria() { return codigoMateria; }
    public void setCodigoMateria(String codigoMateria) { this.codigoMateria = codigoMateria; }

    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }

    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }
}