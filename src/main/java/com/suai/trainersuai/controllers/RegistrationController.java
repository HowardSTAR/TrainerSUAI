package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/enterPage")
    public String enterPageLoad(Model model) {
        System.out.println("enterPage GET");
        User user = new User();
        model.addAttribute("user", user);
        return "enterPage";
    }

    @PostMapping("/enterPage")
    public String registrationEterPage(User user,
                                       @RequestParam("action") String action) {
        System.out.println("enterPage POST registr");
        System.out.println("registrUser = "+user);
        System.out.println("action = " + action);

        if (action.equals("registr")) {
            User saveUser = userService.save(user);

            System.out.println("saveUser = "+saveUser);

            System.out.println("SAVE");
        } else {
            if (!userService.isLoginUser(user)) {

                System.out.println("loginUser false");
                return "enterPage";
            }
        }
        return "redirect:/reaction_game";
    }

}
