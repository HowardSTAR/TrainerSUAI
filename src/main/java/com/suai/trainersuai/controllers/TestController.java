package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.RegistrationUser;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class TestController {

    @GetMapping("/")
    public String indx () {
        return "index" ;
    }

    @GetMapping("/registration")
    public String registration (Model model) {
//        RegistrationUser regUser = new RegistrationUser();
        model.addAttribute("regUser", new RegistrationUser());
        return "registration" ;
    }

    @PostMapping("/save")
    public String actionReg(@Valid @ModelAttribute RegistrationUser regUser, Model model){

        model.addAttribute("regUser", regUser);
        return "index" ;
    }
}
