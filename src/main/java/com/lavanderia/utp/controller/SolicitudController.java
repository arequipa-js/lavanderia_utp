package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.dao.PrendaDAO;
import com.lavanderia.utp.dao.ServicioDAO;
import com.lavanderia.utp.dao.SolicitudDAO;
import com.lavanderia.utp.dao.SolicitudDetalleDAO;
import com.lavanderia.utp.model.Comprobante;
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.model.Prenda;
import com.lavanderia.utp.model.Servicio;
import com.lavanderia.utp.model.Solicitud;
import com.lavanderia.utp.model.SolicitudDetalle;
import com.lavanderia.utp.utils.Common;
import com.lavanderia.utp.utils.EmailService;
import com.lavanderia.utp.utils.PDFService;
import java.util.HashMap;

import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listServicios", listServicios);
        model.addAttribute("listPrendas", listPrendas);
        model.addAttribute("solicitudDetalle", solicitudDetalle);
        return "solicitudes_add";
    }

    @RequestMapping("/solicitud_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Solicitud solicitud = solicitudDAO.getById(id);
        List<Persona> listClientes = personaDAO.getAll('C');
        model.addAttribute("solicitud", solicitud);
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

    @RequestMapping("/solicitud_servicios_add")
    public String showSolicitudServicioForm(@RequestParam int id, Model model) {
        Solicitud solicitud = solicitudDAO.getById(id);
        Persona cliente = personaDAO.getById(solicitud.getPersonaId());
        SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
        List<Servicio> listServicios = servicioDAO.getAll();
        List<Prenda> listPrendas = prendaDAO.getByClienteId(cliente.getId());
        model.addAttribute("solicitud", id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("listServicios", listServicios);
        model.addAttribute("listPrendas", listPrendas);
        model.addAttribute("solicitudDetalle", solicitudDetalle);
        return "solicitud_servicios_add";
    }

    @PostMapping("/solicitud_servicio_add")
    public String addDetalle(@ModelAttribute("solicitudDetalle") SolicitudDetalle solicitudDetalle) {
        solicitudDetalleDAO.add(solicitudDetalle);
        return "redirect:/solicitudes";
    }

    @PostMapping("/solicitud_update")
    public String update(@ModelAttribute("solicitud") Solicitud solicitud) {
        solicitudDAO.update(solicitud);
        return "redirect:/solicitudes";
    }

    @RequestMapping(value = "/enviar_comprobante", method = RequestMethod.POST)
    public @ResponseBody String enviarComprobante(@RequestParam("clienteId") int clienteId, @RequestParam("messageStr") String messageStr) {
        try {
            Persona cliente = personaDAO.getById(clienteId);
            String toEmail = cliente.getEmail();
            String subject = Common.COMPROBANTE_ASUNTO;
            String message = "<h1>Estimado(a): " + cliente.getNombres() + " " + cliente.getApellidos() + "</h1><br>";
            message += Common.COMPROBANTE_MENSAJE + "<br><br>";
            message += messageStr + "<br>";
            message += "<br><br>" + Common.GRACIAS;
            System.out.println("sending email");
            System.out.println(clienteId);
            System.out.println(messageStr);
            EmailService emailService = new EmailService();
            emailService.sendMail(toEmail, subject, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    @RequestMapping("/export_pdf")
    public String exportPDFForm(@RequestParam int solicitudId, @RequestParam int total) throws JRException {
        PDFService pdfService = new PDFService();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("titulo", "Lavanderia UTP");
        Solicitud solicitud = solicitudDAO.getById(solicitudId);
        Persona cliente = personaDAO.getById(solicitud.getPersonaId());
        List<SolicitudDetalle> solicitudDetalles = solicitudDetalleDAO.getBySolicitudId(solicitudId);
        String servicios = "Servicios: ";
        for (SolicitudDetalle sd : solicitudDetalles) {
            servicios += sd.getServicio() + " ";
        }

        System.out.println(servicios);

        parameters.put("cliente", "Cliente: " + cliente.getNombres() + " " + cliente.getApellidos());
        parameters.put("fecha", "Fecha: " + solicitud.getFechaCreacion());
        parameters.put("total", "Total: " + total);
        parameters.put("servicios", servicios);

        pdfService.exportPDF(parameters);
        return "redirect:/solicitudes";
    }

}
