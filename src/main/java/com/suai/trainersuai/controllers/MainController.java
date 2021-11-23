package com.suai.trainersuai.controllers;

import com.suai.trainersuai.model.User;

import com.suai.trainersuai.model.UserRating;
import com.suai.trainersuai.to.UserToRating;
import com.suai.trainersuai.service.UserRatingService;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static com.suai.trainersuai.util.SecurityUtil.*;


@Controller
public class MainController {

    private  String userName = "";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRatingService ratingService;

    @GetMapping("/index")
    public String mainPage() {
        return "index";
    }


    @GetMapping("/secondChance")
    public String reaction (Model model) {

        if (authUserId() == 0) {
//           Вход не выполнен
            return "redirect:/enterPage";
        }
        User user = userService.getUserById(authUserId());
        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userName", userName);

        return "secondChance";
    }

    @PostMapping("/secondChance")
    public String getResult(@RequestParam(value = "myPostVar") Float myPostVar) throws ParseException {

        UserRating userResult = ratingService.saveResult(myPostVar);

        return "redirect:";
    }

    @GetMapping("/leaderBoardPage")
    public String rating(Model model) {

        if (authUserId() == 0) {
//            "Вход не выполнен"
            return "redirect:/enterPage";
        }

        List<UserToRating> userRating = ratingService.getAllMaxRating();

        User user = userService.getUserById(authUserId());
        userName = user.getName() + " " + user.getSecondName();
        model.addAttribute("userRating", userRating);
        model.addAttribute("userName", userName);

        return "leaderBoardPage";
    }

@GetMapping("/aboutUs")
    public String aboutUs() {

        return "aboutUs";
}

}
