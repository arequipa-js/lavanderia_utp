package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.ChoferDAO;
import com.lavanderia.utp.model.Chofer;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChoferController {

    ChoferDAO choferDAO = new ChoferDAO();

    @RequestMapping("/choferes")
    public String listAll(Model model) {
        List<Chofer> listChoferes = choferDAO.getAll();
        model.addAttribute("listChoferes", listChoferes);
        return "choferes";
    }

    @RequestMapping("/choferes_add")
    public String showform(Model model) {
        Chofer chofer = new Chofer();
        model.addAttribute("chofer", chofer);
        return "choferes_add";
    }

    @RequestMapping("/chofer_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Chofer chofer = choferDAO.getById(id);
        model.addAttribute("chofer", chofer);
        return "chofer_edit";
    }

    @RequestMapping("/chofer_delete")
    public String deleteChoferForm(@RequestParam int id) {
        choferDAO.delete(id);
        return "redirect:/choferes";
    }

    @PostMapping("/chofer_add")
    public String add(@ModelAttribute("chofer") Chofer chofer) {
        choferDAO.add(chofer);
        return "redirect:/choferes";
    }

    @PostMapping("/chofer_update")
    public String update(@ModelAttribute("chofer") Chofer chofer) {
        choferDAO.update(chofer);
        return "redirect:/choferes";
    }
}
