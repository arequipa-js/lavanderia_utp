package com.lavanderia.utp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("clienteId")
public class HomeController {

   @RequestMapping("/inicio")  
   public ModelAndView index() {
      String title = "Inicio";
      return new ModelAndView("inicio", "message", title);  
   }
}
