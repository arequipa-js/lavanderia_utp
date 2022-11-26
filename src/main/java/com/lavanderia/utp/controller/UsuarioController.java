package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.model.Persona;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    PersonaDAO personaDAO = new PersonaDAO();

    @RequestMapping("/usuarios")
    public String listAll(Model model) {
        List<Persona> listUsuarios = personaDAO.getPersonas('A', false);
        model.addAttribute("listUsuarios", listUsuarios);
        return "usuarios";
    }

    @RequestMapping("/usuarios_add")
    public String showform(Model model) {
        Persona usuario = new Persona();
        model.addAttribute("usuario", usuario);
        return "usuarios_add";
    }

    @RequestMapping("/usuario_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Persona usuario = personaDAO.getById(id);
        model.addAttribute("usuario", usuario);
        return "usuario_edit";
    }

    @RequestMapping("/usuario_delete")
    public String deleteUsuarioForm(@RequestParam int id) {
        personaDAO.delete(id);
        return "redirect:/usuarios";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("usuario") Persona usuario) {
        personaDAO.addAdmin(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("usuario") Persona usuario) {
        personaDAO.updateAdmin(usuario);
        return "redirect:/usuarios";
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String searchText) {
        List<Persona> listUsuarios = personaDAO.search(searchText);
        ModelAndView modelAndView = new ModelAndView("usuarios");
        modelAndView.addObject("listUsuarios", listUsuarios);
        return modelAndView;
    }
}
