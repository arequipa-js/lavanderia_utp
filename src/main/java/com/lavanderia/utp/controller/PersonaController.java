package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.DistritoDAO;
import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.model.Distrito;
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.utils.Functions;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("clienteId")
public class PersonaController {

    PersonaDAO personaDAO = new PersonaDAO();
    DistritoDAO distritoDAO = new DistritoDAO();

    @RequestMapping("/clientes")
    public String listAll(Model model) {
        Persona cliente = new Persona();
        List<Persona> listClientes = personaDAO.getPersonas('C', false);
        model.addAttribute("cliente", cliente);
        model.addAttribute("listClientes", listClientes);
        return "clientes";
    }

    @RequestMapping("/clientes_add")
    public String showform(Model model) {
        Persona cliente = new Persona();
        List<Distrito> listDistritos = distritoDAO.getAll();
        model.addAttribute("cliente", cliente);
        model.addAttribute("listDistritos", listDistritos);
        return "clientes_add";
    }

    @RequestMapping("/cliente_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Persona cliente = personaDAO.getById(id);
        List<Distrito> listDistritos = distritoDAO.getAll();
        model.addAttribute("cliente", cliente);
        model.addAttribute("listDistritos", listDistritos);
        return "cliente_edit";
    }

    @RequestMapping("/cliente_delete")
    public String deleteClienteForm(@RequestParam int id) {
        personaDAO.delete(id);
        return "redirect:/clientes";
    }

    @PostMapping("/cliente_add")
    public String add(@ModelAttribute("cliente") Persona cliente) {
        personaDAO.add(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/cliente_update")
    public String update(@ModelAttribute("cliente") Persona cliente) {
        personaDAO.update(cliente);
        int clienteId = Functions.getSessionClienteId();
        if (clienteId != 0) {
            return "redirect:/inicio";
        } else {
            return "redirect:/clientes";
        }
    }

    @RequestMapping("/cliente_search")
    public String search(@RequestParam String nombres, Model model) {
        List<Persona> listClientes = personaDAO.search(nombres);
        Persona cliente = new Persona();
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("cliente", cliente);
        return "clientes";
    }
}
