package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.model.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    PersonaDAO personaDAO = new PersonaDAO();

    @RequestMapping("/")
    public String index(Model model) {
        Persona persona = new Persona();
        model.addAttribute("usuario", persona);
        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView index(@ModelAttribute("usuario") Persona persona) {
        boolean isAuthenticated = personaDAO.login(persona.getEmail(), persona.getPassword());
        if (isAuthenticated) {
            return new ModelAndView("inicio");
        } else {
            ModelAndView model = new ModelAndView("index");
            model.addObject("error", "Email y/o contraseña incorrectos");
            return model;
        }
    }
}
