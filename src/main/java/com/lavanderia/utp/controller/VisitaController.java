package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.MovilidadDAO;
import com.lavanderia.utp.dao.SolicitudDAO;
import com.lavanderia.utp.dao.VisitaDAO;
import com.lavanderia.utp.model.Movilidad;
import com.lavanderia.utp.model.Solicitud;
import com.lavanderia.utp.model.Visita;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VisitaController {

    MovilidadDAO movilidadDAO = new MovilidadDAO();
    SolicitudDAO solicitudDAO = new SolicitudDAO();
    VisitaDAO visitaDAO = new VisitaDAO();

    @RequestMapping("/visitas")
    public String listAll(Model model) {
        List<Visita> listVisitas = visitaDAO.getAll();
        model.addAttribute("listVisitas", listVisitas);
        return "visitas";
    }

    @RequestMapping("/visitas_add")
    public String showform(Model model) {
        Visita visita = new Visita();
        List<Movilidad> listMovilidades = movilidadDAO.getAll();
        List<Solicitud> listSolicitudes = solicitudDAO.getAll();
        model.addAttribute("visita", visita);
        model.addAttribute("listMovilidades", listMovilidades);
        model.addAttribute("listSolicitudes", listSolicitudes);
        return "visitas_add";
    }

    @RequestMapping("/visita_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Visita visita = visitaDAO.getById(id);
        List<Movilidad> listMovilidades = movilidadDAO.getAll();
        List<Solicitud> listSolicitudes = solicitudDAO.getAll();
        model.addAttribute("visita", visita);
        model.addAttribute("listMovilidades", listMovilidades);
        model.addAttribute("listSolicitudes", listSolicitudes);
        return "visita_edit";
    }

    @RequestMapping("/visita_delete")
    public String deleteVisitaForm(@RequestParam int id) {
        visitaDAO.delete(id);
        return "redirect:/visitas";
    }

    @PostMapping("/visita_add")
    public String add(@ModelAttribute("visita") Visita visita) {
        visitaDAO.add(visita);
        return "redirect:/visitas";
    }

    @PostMapping("/visita_update")
    public String update(@ModelAttribute("visita") Visita visita) {
        visitaDAO.update(visita);
        return "redirect:/visitas";
    }
}