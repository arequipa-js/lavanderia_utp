package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.CategoriaDAO;
import com.lavanderia.utp.dao.ServicioDAO;
import com.lavanderia.utp.model.Categoria;
import com.lavanderia.utp.model.Servicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServicioController {

    CategoriaDAO categoriaDAO = new CategoriaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();
    

    @RequestMapping("/servicios")
    public String listAll(Model model) {
        List<Servicio> listServicios = servicioDAO.getAll();
        model.addAttribute("listServicios", listServicios);
        return "servicios";
    }

    @RequestMapping("/servicios_add")
    public String showform(Model model) {
        Servicio servicio = new Servicio();
        List<Categoria> listCategorias = categoriaDAO.getAll();
        model.addAttribute("servicio", servicio);
        model.addAttribute("listCategorias", listCategorias);
        return "servicios_add";
    }

    @RequestMapping("/servicio_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Servicio servicio = servicioDAO.getById(id);
        List<Categoria> listCategorias = categoriaDAO.getAll();
        model.addAttribute("servicio", servicio);
        model.addAttribute("listCategorias", listCategorias);
        return "servicio_edit";
    }

    @RequestMapping("/servicio_delete")
    public String deleteServicioForm(@RequestParam int id) {
        servicioDAO.delete(id);
        return "redirect:/servicios";
    }

    @PostMapping("/servicio_add")
    public String add(@ModelAttribute("servicio") Servicio servicio) {
        servicioDAO.add(servicio);
        return "redirect:/servicios";
    }

    @PostMapping("/servicio_update")
    public String update(@ModelAttribute("servicio") Servicio servicio) {
        servicioDAO.update(servicio);
        return "redirect:/servicios";
    }
}
