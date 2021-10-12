package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.RegistrationUser;

import com.suai.trainersuai.persistence.repositories.RegistartionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class TestController {

    @Autowired
    private RegistartionRepository registrator;

    @GetMapping("/")
    public String index () {
        return "index" ;
    }

    @GetMapping("/regist")
    public String registration (Model model) {
//        RegistrationUser regUser = new RegistrationUser();
        model.addAttribute("regUser", new RegistrationUser());
        return "regist";
    }

    @PostMapping("/regist")
    public String actionReg(@RequestParam String name,
                            @RequestParam String secondName,
                            @RequestParam String thirdName,
                            @RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam String info,
                            @RequestParam String avatar,
                            @RequestParam String password,
                            Map<String, Object> model){

        RegistrationUser regUser = new RegistrationUser(name, secondName, thirdName, email, phone, info, avatar, password);
        System.out.println("!!!!!!!!!!!!!!!!!!!!  " + regUser);
        model.put("regUser", new RegistrationUser());

        registrator.save(regUser);

        return "index" ;
    }
}
