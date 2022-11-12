package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.dao.PrendaDAO;
import com.lavanderia.utp.dao.ServicioDAO;
import com.lavanderia.utp.dao.SolicitudDAO;
import com.lavanderia.utp.dao.SolicitudDetalleDAO;
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.model.Prenda;
import com.lavanderia.utp.model.Servicio;
import com.lavanderia.utp.model.Solicitud;
import com.lavanderia.utp.model.SolicitudDetalle;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SolicitudController {

    SolicitudDAO solicitudDAO = new SolicitudDAO();
    SolicitudDetalleDAO solicitudDetalleDAO = new SolicitudDetalleDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();
    PrendaDAO prendaDAO = new PrendaDAO();


    @RequestMapping("/solicitudes")
    public String listAll(Model model) {
        List<SolicitudDetalle> listSolicitudDetalles = solicitudDetalleDAO.getAll();
        model.addAttribute("listSolicitudDetalles", listSolicitudDetalles);
        return "solicitudes";
    }

    @RequestMapping("/solicitudes_add")
    public String showform(Model model) {
        Solicitud solicitud = new Solicitud();
        SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
        List<Persona> listClientes = personaDAO.getAll('C');
        List<Servicio> listServicios = servicioDAO.getAll();
        List<Prenda> listPrendas = prendaDAO.getAll();
        model.addAttribute("solicitud",  solicitud);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listServicios", listServicios);
        model.addAttribute("listPrendas", listPrendas);
        model.addAttribute("solicitudDetalle",  solicitudDetalle);
        return "solicitudes_add";
    }

    @RequestMapping("/solicitud_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Solicitud solicitud = solicitudDAO.getById(id);
        List<Persona> listClientes = personaDAO.getAll('C');
        model.addAttribute("solicitud",  solicitud);
        model.addAttribute("listClientes", listClientes);
        return "solicitud_edit";
    }

    @RequestMapping("/comprobante")
    public String showformComprobante(@RequestParam int id, Model model) {
        List<SolicitudDetalle> solicitudDetalles = solicitudDetalleDAO.getBySolicitudId(id);
        Solicitud solicitud = solicitudDAO.getById(id);
        Persona cliente = personaDAO.getById(solicitud.getPersonaId());
        model.addAttribute("cliente", cliente);
        model.addAttribute("listSolicitudDetalles", solicitudDetalles);
        model.addAttribute("solicitud", id);
        model.addAttribute("fechaSolicitud", solicitud.getFechaCreacion());
        return "comprobante";
    }

    @RequestMapping("/solicitud_delete")
    public String deleteSolicitudForm(@RequestParam int id) {
        solicitudDAO.delete(id);
        return "redirect:/solicitudes";
    }

    @PostMapping("/solicitud_add")
    public String add(@ModelAttribute("solicitud") Solicitud solicitud) {
        solicitudDAO.add(solicitud);
        return "redirect:/solicitudes";
    }
    
    @PostMapping("/solicitud_detalle_add")
    public String addDetalle(@ModelAttribute("solicitudDetalle") SolicitudDetalle solicitudDetalle) {
        solicitudDetalleDAO.add(solicitudDetalle);
        return "redirect:/solicitudes";
    }

    @PostMapping("/solicitud_update")
    public String update(@ModelAttribute("solicitud") Solicitud solicitud) {
        solicitudDAO.update(solicitud);
        return "redirect:/solicitudes";
    }
}
