package com.universidad.gestioncursos.controller;

import com.universidad.gestioncursos.model.Carrera;
import com.universidad.gestioncursos.repository.CarreraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    // LISTAR todas las carreras
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("carreras", carreraRepository.findAll());
        model.addAttribute("titulo", "Listado de Carreras");
        return "carreras/lista";
    }

    // MOSTRAR formulario para CREAR
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("carrera", new Carrera());
        model.addAttribute("titulo", "Nueva Carrera");
        model.addAttribute("accion", "Crear");
        return "carreras/formulario";
    }

    // GUARDAR nueva carrera
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Carrera carrera,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nueva Carrera");
            model.addAttribute("accion", "Crear");
            return "carreras/formulario";
        }

        carreraRepository.save(carrera);
        flash.addFlashAttribute("success", "Carrera creada exitosamente");
        return "redirect:/carreras";
    }

    // MOSTRAR formulario para EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id,
                                          Model model,
                                          RedirectAttributes flash) {
        var carrera = carreraRepository.findById(id);
        if (carrera.isEmpty()) {
            flash.addFlashAttribute("error", "La carrera no existe");
            return "redirect:/carreras";
        }

        model.addAttribute("carrera", carrera.get());
        model.addAttribute("titulo", "Editar Carrera");
        model.addAttribute("accion", "Editar");
        return "carreras/formulario";
    }

    // ACTUALIZAR carrera
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Carrera carrera,
                             BindingResult result,
                             Model model,
                             RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Carrera");
            model.addAttribute("accion", "Editar");
            return "carreras/formulario";
        }

        carrera.setId(id);
        carreraRepository.save(carrera);
        flash.addFlashAttribute("success", "Carrera actualizada exitosamente");
        return "redirect:/carreras";
    }

    // ELIMINAR carrera
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        carreraRepository.deleteById(id);
        flash.addFlashAttribute("success", "Carrera eliminada exitosamente");
        return "redirect:/carreras";
    }
}