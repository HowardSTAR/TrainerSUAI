package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.suai.trainersuai.util.SecurityUtil.authUserId;

@Controller
public class ProfileUserController {

    private  String userName = "";

    @Autowired
    private UserService userService;

    @GetMapping("/editFormUser")
    public String editFormUser(Model model) {

        if (authUserId() == 0) {
            System.out.println("Вход не выполнен");
            return "redirect:/enterPage";
        }

        User user = userService.getUserById(authUserId());
        System.out.println("User: "+user);

        userName = user.getName() + " " + user.getSecondName();

        model.addAttribute("user", user);
        model.addAttribute("userName", userName);

        return "editFormUser";
    }

    @PostMapping("/editFormUser")
    public String edtiFormUserChange(User user, Model model) {

        user.setId(authUserId());

        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userName", userName);


        System.out.println(userService.save(user));

        return "editFormUser";
    }

}
