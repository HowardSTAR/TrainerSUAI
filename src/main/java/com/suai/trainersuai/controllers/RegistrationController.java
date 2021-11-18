package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLIntegrityConstraintViolationException;

import static com.suai.trainersuai.util.SecurityUtil.*;

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
                                       @RequestParam("action") String action,
                                       Model model) {
        System.out.println("enterPage POST registr");
        System.out.println("registrUser = "+user);
        System.out.println("action = " + action);

        if (action.equals("registration")) {

            ExceptionAlert isException = new ExceptionAlert(false);
            try {

                User saveUser = userService.save(user);

                System.out.println("saveUser = "+saveUser);

            } catch (Exception e) {
                System.out.println("Такой Email уже существует");
                String exception = "Такой Email уже существует";
//                String isException = "1";
                isException = new ExceptionAlert(true);
                model.addAttribute("isException", isException);
                model.addAttribute("exception", exception);
                return "enterPage";
            }



            System.out.println("SAVE");
        } else {
            if (!userService.isLoginUser(user)) {

                System.out.println("loginUser false");
//                setAuthUserId(userService.getByEmail(user.getEmail()));
                return "enterPage";
            }
        }
        return "redirect:/reaction_game";
    }

    @GetMapping("/exit")
    public String exit() {
        setAuthUserId(0);
        return "redirect:/enterPage";
    }


    class ExceptionAlert {
        private boolean isException;

        public ExceptionAlert(boolean isException) {
            this.isException = isException;
        }

        public ExceptionAlert() {
            this.isException = false;
        }
    }

}
