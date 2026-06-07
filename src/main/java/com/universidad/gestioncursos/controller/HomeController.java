package com.universidad.gestioncursos.controller;

import com.universidad.gestioncursos.repository.CarreraRepository;
import com.universidad.gestioncursos.repository.EstudianteRepository;
import com.universidad.gestioncursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalCarreras", carreraRepository.count());
        model.addAttribute("totalEstudiantes", estudianteRepository.count());
        model.addAttribute("totalCursos", cursoRepository.count());
        return "index";
    }
}