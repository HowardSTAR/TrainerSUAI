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

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String mainPage() {
        return "index";
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

        UserRating userResult = userService.saveResult(myPostVar);

        System.out.println("userResult = " + userResult);

        return "redirect:";
    }

    @GetMapping("/rating")
    public String rating(Model model) {

        List<UserToRating> userRating = userService.getAllRating();
        model.addAttribute("userRating", userRating);
        return "rating";
    }

    @GetMapping("/editFormUser")
    public String editFormUser(Model model) {
        if (authUserId() == 0) {
            System.out.println("Пользователь не зарегистрирован");
            return "enterPage";
        }

        User user = userService.getUserById(authUserId());
        System.out.println("User: "+user);
        model.addAttribute("user", user);

        return "editFormUser";
    }

    @PostMapping("/editFormUser")
    public String edtiFormUserChange(User user) {

        user.setId(authUserId());


        System.out.println(userService.save(user));

        return "index";
    }

}
