package com.universidad.gestioncursos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String carnet;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    private String email;

    private String telefono;

    private Boolean activo = true;

    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos = new HashSet<>();

    public Estudiante() {}

    public Estudiante(String carnet, String nombres, String apellidos, String email, String telefono) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Set<Curso> getCursos() { return cursos; }
    public void setCursos(Set<Curso> cursos) { this.cursos = cursos; }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}