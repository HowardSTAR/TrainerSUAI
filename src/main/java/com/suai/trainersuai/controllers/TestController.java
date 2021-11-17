package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;

import com.suai.trainersuai.persistence.entities.UserRating;
import com.suai.trainersuai.persistence.to.UserToRating;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static com.suai.trainersuai.util.SecurityUtil.*;


@Controller
public class TestController {

    private  String userName = "";

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String mainPage() {
        return "index";
    }


    @GetMapping("/reaction_game")
    public String reaction (Model model) {

        if (authUserId() == 0) {
            System.out.println("Вход не выполнен");
            return "redirect:/enterPage";
        }

        System.out.println("reaction_game - GET");
        User user = userService.getUserById(authUserId());
        System.out.println("Game user: "+user);
        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userName", userName);

        return "reaction_game";
    }

    @PostMapping("/reaction_game")
    public String getResult(@RequestParam(value = "myPostVar") Float myPostVar) throws ParseException {
        System.out.println("POST result");
        System.out.println("result = "+myPostVar);

        UserRating userResult = userService.saveResult(myPostVar);

        System.out.println("userResult = " + userResult);

        return "redirect:";
    }

    @GetMapping("/rating")
    public String rating(Model model) {

        if (authUserId() == 0) {
            System.out.println("Вход не выполнен");
            return "redirect:/enterPage";
        }

        List<UserToRating> userRating = userService.getAllRating();
        User user = userService.getUserById(authUserId());

        System.out.println("rating user: "+user);

        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userRating", userRating);
        model.addAttribute("userName", userName);
        return "rating";
    }

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
