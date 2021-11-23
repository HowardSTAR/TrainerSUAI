package com.suai.trainersuai.controllers;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.suai.trainersuai.util.SecurityUtil.authUserId;

@Controller
public class ProfileUserController {

    private  String userName = "";

    @Autowired
    private UserService userService;

    @GetMapping("/profilePage")
    public String editFormUser(Model model) {

        if (authUserId() == 0) {
            System.out.println( "Вход не выполнен");
            return "redirect:/enterPage";
        }
        User user = userService.getUserById(authUserId());
        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("user", user);
        model.addAttribute("userName", userName);
        return "profilePage";
    }

    @PostMapping("/profilePage")
    public String edtiFormUserChange(User user,
                                     Model model,
                                    @RequestParam("action") String action) {
        if (action.equals("exit")) {
            return "redirect:/exit";
        }
        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userName", userName);
        if (userService.save(user).equals(null)) {
            return "enterPage";
        }
        return "profilePage";
    }

}
