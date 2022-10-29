package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.dao.PrendaColorDAO;
import com.lavanderia.utp.dao.PrendaDAO;
import com.lavanderia.utp.dao.PrendaEstadoDAO;
import com.lavanderia.utp.dao.PrendaMaterialDAO;
import com.lavanderia.utp.dao.PrendaTipoDAO;
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.model.Prenda;
import com.lavanderia.utp.model.PrendaColor;
import com.lavanderia.utp.model.PrendaEstado;
import com.lavanderia.utp.model.PrendaMaterial;
import com.lavanderia.utp.model.PrendaTipo;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrendaController {

    PersonaDAO personaDAO = new PersonaDAO();
    PrendaDAO prendaDAO = new PrendaDAO();
    PrendaColorDAO prendaColorDAO = new PrendaColorDAO();
    PrendaTipoDAO prendaTipoDAO = new PrendaTipoDAO();
    PrendaMaterialDAO prendaMaterialDAO = new PrendaMaterialDAO();
    PrendaEstadoDAO prendaEstadoDAO = new PrendaEstadoDAO();

    @RequestMapping("/prendas")
    public String listAll(Model model) {
        List<Prenda> listPrendas = prendaDAO.getAll();
        model.addAttribute("listPrendas", listPrendas);
        return "prendas";
    }

    @RequestMapping("/prendas_add")
    public String showform(Model model) {
        Prenda prenda = new Prenda();
        List<Persona> listClientes = personaDAO.getAll('C');
        List<PrendaColor> listColores = prendaColorDAO.getAll();
        List<PrendaTipo> listTipos = prendaTipoDAO.getAll();
        List<PrendaMaterial> listMateriales = prendaMaterialDAO.getAll();
        List<PrendaEstado> listEstados = prendaEstadoDAO.getAll();
        model.addAttribute("prenda", prenda);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listColores", listColores);
        model.addAttribute("listTipos", listTipos);
        model.addAttribute("listMateriales", listMateriales);
        model.addAttribute("listEstados", listEstados);
        return "prendas_add";
    }

    @RequestMapping("/prenda_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Prenda prenda = prendaDAO.getById(id);
        List<Persona> listClientes = personaDAO.getAll('C');
        List<PrendaColor> listColores = prendaColorDAO.getAll();
        List<PrendaTipo> listTipos = prendaTipoDAO.getAll();
        List<PrendaMaterial> listMateriales = prendaMaterialDAO.getAll();
        List<PrendaEstado> listEstados = prendaEstadoDAO.getAll();
        model.addAttribute("prenda", prenda);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listColores", listColores);
        model.addAttribute("listTipos", listTipos);
        model.addAttribute("listMateriales", listMateriales);
        model.addAttribute("listEstados", listEstados);
        return "prenda_edit";
    }

    @RequestMapping("/prenda_delete")
    public String deletePrendaForm(@RequestParam int id) {
        prendaDAO.delete(id);
        return "redirect:/prendas";
    }

    @PostMapping("/prenda_add")
    public String add(@ModelAttribute("prenda") Prenda prenda) {
        prendaDAO.add(prenda);
        return "redirect:/prendas";
    }

    @PostMapping("/prenda_update")
    public String update(@ModelAttribute("prenda") Prenda prenda) {
        prendaDAO.update(prenda);
        return "redirect:/prendas";
    }
}
