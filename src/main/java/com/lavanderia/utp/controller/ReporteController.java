package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.CategoriaDAO;
import com.lavanderia.utp.dao.PersonaDAO;
import com.lavanderia.utp.dao.ReporteDAO;
import com.lavanderia.utp.dao.ServicioDAO;
import com.lavanderia.utp.model.Categoria;
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.model.Servicio;
import com.lavanderia.utp.model.SolicitudDetalle;
import com.lavanderia.utp.utils.PDFService;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReporteController {

    ReporteDAO reporteDAO = new ReporteDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();

    @RequestMapping("/reportes")
    public String listAll(@RequestParam(defaultValue = "0") int personaId, @RequestParam(defaultValue = "0") int categoriaId, @RequestParam(defaultValue = "0") int servicioId, Model model) {
        List<Persona> listClientes = personaDAO.getPersonas('C', true);
        List<Categoria> listCategorias = categoriaDAO.getByActivo(true);
        List<Servicio> listServicios = servicioDAO.getByActivo(true);
        SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
        solicitudDetalle.setPersonaId(personaId);
        solicitudDetalle.setCategoriaId(categoriaId);
        solicitudDetalle.setServicioId(servicioId);
        List<SolicitudDetalle> listReportes = reporteDAO.search(personaId, categoriaId, servicioId);
        model.addAttribute("listClientes", listClientes);
        model.addAttribute("listCategorias", listCategorias);
        model.addAttribute("listServicios", listServicios);
        model.addAttribute("listReportes", listReportes);
        model.addAttribute("solicitudDetalle", solicitudDetalle);
        model.addAttribute("nroResultados", listReportes.size());
        return "reportes";
    }

    @RequestMapping(value = "/export_pdf", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody String exportPDF(@RequestParam("personaId") int personaId, @RequestParam("categoriaId") int categoriaId, @RequestParam("servicioId") int servicioId) {
        PDFService pdfService = new PDFService();
        HashMap<String, Object> sqlParameters = new HashMap<>();
        sqlParameters.put("personaId", personaId);
        sqlParameters.put("categoriaId", categoriaId);
        sqlParameters.put("servicioId", servicioId);
        try {
            pdfService.exportPDF(sqlParameters);
        } catch (JRException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
