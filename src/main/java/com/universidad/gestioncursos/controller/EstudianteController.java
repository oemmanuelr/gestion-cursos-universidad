package com.universidad.gestioncursos.controller;

import com.universidad.gestioncursos.model.Estudiante;
import com.universidad.gestioncursos.model.Curso;
import com.universidad.gestioncursos.repository.EstudianteRepository;
import com.universidad.gestioncursos.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // LISTAR estudiantes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("titulo", "Listado de Estudiantes");
        return "estudiantes/lista";
    }

    // MOSTRAR formulario para CREAR
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("titulo", "Nuevo Estudiante");
        model.addAttribute("accion", "Crear");
        return "estudiantes/formulario";
    }

    // GUARDAR estudiante
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Estudiante estudiante,
                          BindingResult result,
                          @RequestParam(value = "cursosIds", required = false) Long[] cursosIds,
                          Model model,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("cursos", cursoRepository.findAll());
            model.addAttribute("titulo", "Nuevo Estudiante");
            model.addAttribute("accion", "Crear");
            return "estudiantes/formulario";
        }

        // Verificar si el carnet ya existe
        if (estudianteRepository.findByCarnet(estudiante.getCarnet()).isPresent()) {
            model.addAttribute("error", "El carnet ya está registrado");
            model.addAttribute("cursos", cursoRepository.findAll());
            model.addAttribute("titulo", "Nuevo Estudiante");
            model.addAttribute("accion", "Crear");
            return "estudiantes/formulario";
        }

        // Asignar cursos seleccionados (ManyToMany)
        if (cursosIds != null) {
            for (Long cursoId : cursosIds) {
                cursoRepository.findById(cursoId).ifPresent(estudiante.getCursos()::add);
            }
        }

        estudianteRepository.save(estudiante);
        flash.addFlashAttribute("success", "Estudiante creado exitosamente");
        return "redirect:/estudiantes";
    }

    // MOSTRAR DETALLE del estudiante
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, RedirectAttributes flash) {
        var estudiante = estudianteRepository.findById(id);
        if (estudiante.isEmpty()) {
            flash.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/estudiantes";
        }

        model.addAttribute("estudiante", estudiante.get());
        model.addAttribute("titulo", "Detalle del Estudiante");
        return "estudiantes/detalle";
    }

    // MOSTRAR formulario para EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id,
                                          Model model,
                                          RedirectAttributes flash) {
        var estudiante = estudianteRepository.findById(id);
        if (estudiante.isEmpty()) {
            flash.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/estudiantes";
        }

        model.addAttribute("estudiante", estudiante.get());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("cursosSeleccionados", estudiante.get().getCursos());
        model.addAttribute("titulo", "Editar Estudiante");
        model.addAttribute("accion", "Editar");
        return "estudiantes/formulario";
    }

    // ACTUALIZAR estudiante
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Estudiante estudiante,
                             BindingResult result,
                             @RequestParam(value = "cursosIds", required = false) Long[] cursosIds,
                             Model model,
                             RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("cursos", cursoRepository.findAll());
            model.addAttribute("titulo", "Editar Estudiante");
            model.addAttribute("accion", "Editar");
            return "estudiantes/formulario";
        }

        estudiante.setId(id);
        estudiante.getCursos().clear();
        if (cursosIds != null) {
            for (Long cursoId : cursosIds) {
                cursoRepository.findById(cursoId).ifPresent(estudiante.getCursos()::add);
            }
        }

        estudianteRepository.save(estudiante);
        flash.addFlashAttribute("success", "Estudiante actualizado exitosamente");
        return "redirect:/estudiantes";
    }

    // ELIMINAR estudiante
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        estudianteRepository.deleteById(id);
        flash.addFlashAttribute("success", "Estudiante eliminado exitosamente");
        return "redirect:/estudiantes";
    }
}