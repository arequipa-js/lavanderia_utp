package com.lavanderia.utp.controller;

import com.lavanderia.utp.dao.CategoriaDAO;
import com.lavanderia.utp.model.Categoria;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoriaController {

    CategoriaDAO categoriaDAO = new CategoriaDAO();

    @RequestMapping("/categorias")
    public String listAll(Model model) {
        List<Categoria> listCategorias = categoriaDAO.getAll();
        model.addAttribute("listCategorias", listCategorias);
        return "categorias";
    }

    @RequestMapping("/categorias_add")
    public String showform(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "categorias_add";
    }

    @RequestMapping("/categoria_edit")
    public String showformEdit(@RequestParam int id, Model model) {
        Categoria categoria = categoriaDAO.getById(id);
        model.addAttribute("categoria", categoria);
        return "categoria_edit";
    }

    @RequestMapping("/categoria_delete")
    public String deleteCategoriaForm(@RequestParam int id) {
        categoriaDAO.delete(id);
        return "redirect:/categorias";
    }

    @PostMapping("/categoria_add")
    public String add(@ModelAttribute("categoria") Categoria categoria) {
        categoriaDAO.add(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/categoria_update")
    public String update(@ModelAttribute("categoria") Categoria categoria) {
        categoriaDAO.update(categoria);
        return "redirect:/categorias";
    }
}
