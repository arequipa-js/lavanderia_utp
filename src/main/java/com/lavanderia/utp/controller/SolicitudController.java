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
import com.lavanderia.utp.utils.Common;
import com.lavanderia.utp.utils.EmailService;
import com.lavanderia.utp.utils.Functions;
import com.lavanderia.utp.utils.PDFService;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("clienteId")
public class SolicitudController {

    SolicitudDAO solicitudDAO = new SolicitudDAO();
    SolicitudDetalleDAO solicitudDetalleDAO = new SolicitudDetalleDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();
    PrendaDAO prendaDAO = new PrendaDAO();

    @RequestMapping("/solicitudes")
    public String listAll(@RequestParam(defaultValue = "*") String estado, Model model) {
        List<Persona> listClientes = new ArrayList<>();
        int clienteId = Functions.getSessionClienteId();
        if (clienteId != 0) {
            Persona persona = personaDAO.getById(clienteId);
            listClientes.add(persona);
        } else {
            listClientes = personaDAO.getPersonas('C', true);
        }
        List<Solicitud> listSolicitudes  = solicitudDAO.getByEstado(estado.charAt(0), clienteId);
        Solicitud solicitud = new Solicitud();
        model.addAttribute("listSolicitudes", listSolicitudes);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("solicitud", solicitud);
        return "solicitudes";
    }

    @RequestMapping("/solicitud_search")
    public String search(int personaId, Model model) {
        List<Solicitud> listSolicitudes = solicitudDAO.search(personaId);
        List<Persona> listClientes = personaDAO.getPersonas('C', true);
        Solicitud solicitud = new Solicitud();
        model.addAttribute("listSolicitudes", listSolicitudes);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("solicitud", solicitud);
        return "solicitudes";
    }

    @RequestMapping("/solicitudes_add")
    public String showform(@RequestParam(defaultValue = "0") int id, Model model) {
        Solicitud solicitud;
        List<SolicitudDetalle> solicitudDetalles = null;
        List<Persona> listClientes = new ArrayList<>();
        List<Prenda> listPrendas = null;
        if (id > 0) {
            solicitud = solicitudDAO.getById(id);
            solicitudDetalles = solicitudDetalleDAO.getBySolicitudId(id);
            Persona cliente = personaDAO.getById(solicitud.getPersonaId());
            listPrendas = prendaDAO.getByClienteId(cliente.getId());
            listClientes = new ArrayList<>();
            listClientes.add(cliente);
        } else {
            solicitud = new Solicitud();
            int clienteId = Functions.getSessionClienteId();
            if (clienteId != 0) {
                Persona persona = personaDAO.getById(clienteId);
                listPrendas = prendaDAO.getByClienteId(clienteId);
                listClientes.add(persona);
            } else {
                listPrendas = prendaDAO.getAll();
                listClientes = personaDAO.getPersonas('C', true);
            }
        }

        List<Servicio> listServicios = servicioDAO.getByActivo(true);
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listServicios", listServicios);
        model.addAttribute("listPrendas", listPrendas);
        model.addAttribute("listSolicitudDetalles", solicitudDetalles);
        return "solicitudes_add";
    }

    @RequestMapping("/solicitud_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Solicitud solicitud = solicitudDAO.getById(id);
        List<Persona> listClientes = personaDAO.getPersonas('C', true);
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("solicitudId", id);
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
        model.addAttribute("fechaSolicitud", solicitud.getFechaSolicitud());
        return "comprobante";
    }

    @RequestMapping("/solicitud_delete")
    public String deleteSolicitudForm(@RequestParam int id) {
        solicitudDAO.delete(id);
        return "redirect:/solicitudes";
    }

    @RequestMapping("/solicitud_detalle_delete")
    public String deleteSolicitudForm(@RequestParam int id, int solicitudId) {
        solicitudDetalleDAO.delete(id);
        return "redirect:/solicitudes_add.html?id=" + solicitudId;
    }

    @PostMapping("/solicitud_add")
    public String add(@ModelAttribute("solicitud") Solicitud solicitud) {
        int solicitudId = solicitud.getId();
        if (solicitudId == 0) {
            solicitudId = solicitudDAO.addNew(solicitud);
        } else {
            SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
            solicitudDetalle.setSolicitudId(solicitud.getId());
            solicitudDetalle.setServicioId(solicitud.getServicioId());
            solicitudDetalle.setPrendaId(solicitud.getPrendaId());
            solicitudDetalle.setObservaciones(solicitud.getObservaciones());
            solicitudDetalleDAO.add(solicitudDetalle);
        }
        return "redirect:/solicitudes_add.html?id=" + solicitudId;
    }

    @RequestMapping("/solicitud_servicios_add")
    public String showSolicitudServicioForm(@RequestParam int id, Model model) {
        Solicitud solicitud = solicitudDAO.getById(id);
        Persona cliente = personaDAO.getById(solicitud.getPersonaId());
        SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
        List<Servicio> listServicios = servicioDAO.getByActivo(true);
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

    @RequestMapping(value = "/prendas_cliente", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody List<Prenda> testing(@RequestParam("clienteId") int clienteId) {
       List<Prenda> listPrendas = prendaDAO.getByClienteId(clienteId);
       return listPrendas;
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

    /*@RequestMapping("/export_pdf_comprobante")
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
        parameters.put("fecha", "Fecha: " + solicitud.getFechaSolicitud());
        parameters.put("total", "Total: " + total);
        parameters.put("servicios", servicios);

        pdfService.exportPDF();
        return "redirect:/solicitudes";
    }*/
}
