package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.ChoferDAO;
import com.lavanderia.utp.dao.MovilidadDAO;
import com.lavanderia.utp.model.Chofer;
import com.lavanderia.utp.model.Movilidad;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovilidadController {

    ChoferDAO choferDAO = new ChoferDAO();
    MovilidadDAO movilidadDAO = new MovilidadDAO();

    @RequestMapping("/movilidades")
    public String listAll(Model model) {
        List<Movilidad> listMovilidades = movilidadDAO.getByActivo(false);
        model.addAttribute("listMovilidades", listMovilidades);
        return "movilidades";
    }

    @RequestMapping("/movilidades_add")
    public String showform(Model model) {
        Movilidad movilidad = new Movilidad();
        List<Chofer> listChoferes = choferDAO.getAll();
        model.addAttribute("movilidad", movilidad);
        model.addAttribute("listChoferes", listChoferes);
        return "movilidades_add";
    }

    @RequestMapping("/movilidad_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Movilidad movilidad = movilidadDAO.getById(id);
        List<Chofer> listChoferes = choferDAO.getAll();
        model.addAttribute("movilidad", movilidad);
        model.addAttribute("listChoferes", listChoferes);
        return "movilidad_edit";
    }

    @RequestMapping("/movilidad_delete")
    public String deleteMovilidadForm(@RequestParam int id) {
        movilidadDAO.delete(id);
        return "redirect:/movilidades";
    }

    @PostMapping("/movilidad_add")
    public String add(@ModelAttribute("movilidad") Movilidad movilidad) {
        movilidadDAO.add(movilidad);
        return "redirect:/movilidades";
    }

    @PostMapping("/movilidad_update")
    public String update(@ModelAttribute("movilidad") Movilidad movilidad) {
        movilidadDAO.update(movilidad);
        return "redirect:/movilidades";
    }
}
