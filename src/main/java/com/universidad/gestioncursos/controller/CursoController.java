package com.universidad.gestioncursos.controller;

import com.universidad.gestioncursos.model.Curso;
import com.universidad.gestioncursos.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("titulo", "Listado de Cursos");
        return "cursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("titulo", "Nuevo Curso");
        model.addAttribute("accion", "Crear");
        return "cursos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Curso curso,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Curso");
            model.addAttribute("accion", "Crear");
            return "cursos/formulario";
        }

        if (cursoRepository.findByCodigo(curso.getCodigo()).isPresent()) {
            model.addAttribute("error", "El código del curso ya existe");
            model.addAttribute("titulo", "Nuevo Curso");
            model.addAttribute("accion", "Crear");
            return "cursos/formulario";
        }

        cursoRepository.save(curso);
        flash.addFlashAttribute("success", "Curso creado exitosamente");
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id,
                                          Model model,
                                          RedirectAttributes flash) {
        var curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {
            flash.addFlashAttribute("error", "El curso no existe");
            return "redirect:/cursos";
        }

        model.addAttribute("curso", curso.get());
        model.addAttribute("titulo", "Editar Curso");
        model.addAttribute("accion", "Editar");
        return "cursos/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Curso curso,
                             BindingResult result,
                             Model model,
                             RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Curso");
            model.addAttribute("accion", "Editar");
            return "cursos/formulario";
        }

        curso.setId(id);
        cursoRepository.save(curso);
        flash.addFlashAttribute("success", "Curso actualizado exitosamente");
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        cursoRepository.deleteById(id);
        flash.addFlashAttribute("success", "Curso eliminado exitosamente");
        return "redirect:/cursos";
    }
}