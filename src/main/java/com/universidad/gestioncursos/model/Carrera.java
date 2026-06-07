package com.universidad.gestioncursos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrera")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Integer duracionSemestres;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<Materia> materias = new ArrayList<>();

    // Constructor vacío (obligatorio para JPA)
    public Carrera() {}

    // Constructor con parámetros
    public Carrera(String codigo, String nombre, String descripcion, Integer duracionSemestres) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionSemestres = duracionSemestres;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getDuracionSemestres() { return duracionSemestres; }
    public void setDuracionSemestres(Integer duracionSemestres) { this.duracionSemestres = duracionSemestres; }

    public List<Materia> getMaterias() { return materias; }
    public void setMaterias(List<Materia> materias) { this.materias = materias; }
}