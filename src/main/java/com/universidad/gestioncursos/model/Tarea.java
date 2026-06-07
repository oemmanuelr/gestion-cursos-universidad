package com.universidad.gestioncursos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private LocalDate fechaEntrega;

    private Integer puntuacionMaxima;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Tarea() {}

    public Tarea(String titulo, String descripcion, LocalDate fechaEntrega, Integer puntuacionMaxima, Curso curso) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.puntuacionMaxima = puntuacionMaxima;
        this.curso = curso;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Integer getPuntuacionMaxima() { return puntuacionMaxima; }
    public void setPuntuacionMaxima(Integer puntuacionMaxima) { this.puntuacionMaxima = puntuacionMaxima; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}