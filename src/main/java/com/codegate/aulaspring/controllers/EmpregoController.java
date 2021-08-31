package com.codegate.aulaspring.controllers;

import com.codegate.aulaspring.models.Emprego;
import com.codegate.aulaspring.repository.EmpregoRepository;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpregoController {

    @Autowired
    EmpregoRepository empregoRepository;

    @RequestMapping("/")
    public String listaEmpregos(Model model){
        model.addAttribute("empregos", empregoRepository.findAll());
        return "lista";
    }

    @GetMapping("/add")
    public String empregoForm(Model model){
        model.addAttribute("emprego", new Emprego());
        return "empregoForm";
    }

    @PostMapping("/process")
    public String processForm(@Validated Emprego emprego, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "empregoForm";
        }
        empregoRepository.save(emprego);
        return "redirect:/";
    }
}
