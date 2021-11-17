package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.RegistrationUser;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.repositories.RegistartionRepository;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@Controller
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/enterPage")
    public String enterPageLoad(Model model) {
        System.out.println("enterPage GET");
        RegistrationUser registrationUser = new RegistrationUser();
        model.addAttribute("registrationUser", registrationUser);
        return "enterPage";
    }

    @PostMapping("/enterPage")
    public String registrationEterPage(RegistrationUser registrationUser,
                                       @RequestParam("action") String action) {
        System.out.println("enterPage POST registr");
        System.out.println("registrUser = "+registrationUser);
        System.out.println("action = " + action);

        if (action.equals("registr")) {
            RegistrationUser saveUser = userService.save(registrationUser);

            System.out.println("saveUser = "+saveUser);

            System.out.println("SAVE");
        } else {
            if (!userService.isLoginUser(registrationUser)) {
                System.out.println("loginUser false");
                return "enterPage";
            }
        }
        return "redirect:/reaction_game";
    }

    @GetMapping("/reaction_game")
    public String reaction (Model model) {
        System.out.println("reaction_game - GET");

        return "reaction_game";
    }

    @PostMapping("/reaction_game")
    public String getResult(@RequestParam(value = "myPostVar") Float myPostVar) throws ParseException {
        System.out.println("POST result");
        System.out.println("result = "+myPostVar);

        User userResult = userService.saveResult(myPostVar);

        System.out.println("userResult = " + userResult);

        return "redirect:";
    }

}
