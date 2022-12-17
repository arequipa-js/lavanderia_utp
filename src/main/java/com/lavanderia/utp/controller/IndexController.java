package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.model.Persona;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(@ModelAttribute("usuario") Persona persona, HttpSession session) {
        Persona personaLogged = personaDAO.login(persona.getEmail(), persona.getPassword());
        if (personaLogged != null) {
            session.setAttribute("nombre", personaLogged.getNombres());
            session.setAttribute("apellidos", personaLogged.getApellidos());
            if (personaLogged.getTipoPersona() == 'C') {
                session.setAttribute("clienteId", personaLogged.getId());
            } else {
                session.setAttribute("clienteId", 0);
            }
            return "redirect:/inicio";
        } else {
            //model.addObject("error", "Email y/o contraseña incorrectos");
            return "";
        }
    }
}
