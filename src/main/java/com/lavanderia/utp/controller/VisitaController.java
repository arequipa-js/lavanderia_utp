package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.MovilidadDAO;
import com.lavanderia.utp.dao.SolicitudDAO;
import com.lavanderia.utp.dao.VisitaDAO;
import com.lavanderia.utp.model.Movilidad;
import com.lavanderia.utp.model.Solicitud;
import com.lavanderia.utp.model.Visita;
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
public class VisitaController {

    MovilidadDAO movilidadDAO = new MovilidadDAO();
    SolicitudDAO solicitudDAO = new SolicitudDAO();
    VisitaDAO visitaDAO = new VisitaDAO();

    @RequestMapping("/visitas")
    public String listAll(@RequestParam(defaultValue = "*") String estado, Model model) {
        int clienteId = Functions.getSessionClienteId();
        List<Visita> listVisitas = visitaDAO.getByEstado(estado.charAt(0), clienteId);
        model.addAttribute("listVisitas", listVisitas);
        return "visitas";
    }

    @RequestMapping("/visitas_add")
    public String showform(Model model) {
        Visita visita = new Visita();
        List<Movilidad> listMovilidades = movilidadDAO.getByActivo(true);
        List<Solicitud> listSolicitudes = solicitudDAO.getByEstado('A', 0);
        model.addAttribute("visita", visita);
        model.addAttribute("listMovilidades", listMovilidades);
        model.addAttribute("listSolicitudes", listSolicitudes);
        return "visitas_add";
    }

    @RequestMapping("/visita_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Visita visita = visitaDAO.getById(id);
        List<Movilidad> listMovilidades = movilidadDAO.getByActivo(true);
        List<Solicitud> listSolicitudes = solicitudDAO.getByEstado('A', 0);
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
