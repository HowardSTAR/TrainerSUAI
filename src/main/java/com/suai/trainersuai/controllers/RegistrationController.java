package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.suai.trainersuai.util.SecurityUtil.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserValidaotr userValidaotr;

    @Autowired
    private UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidaotr);
    }

    @GetMapping("/enterPage")
    public String enterPageLoad(@ModelAttribute User user) {
//        User user = new User();
//        model.addAttribute("user", user);

        return "enterPage";
    }

    @PostMapping("/enterPage")
    public String registrationEterPage(@ModelAttribute @Valid User user,
                                       BindingResult bindingResult,
                                       @RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("action") String action,
//                                       Model model,
                                       final RedirectAttributes redirectAttributes) {

//        REGISTRATION
        if (action.equals("isRegistration")) {
            try {
                if (bindingResult.hasErrors()) {
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
                userService.save(user);
            } catch (Exception e) {
                bindingResult.rejectValue("email", "error.email", "Такой Email уже существует");
                if (bindingResult.hasErrors()) {
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
            }
        } else {
//            LOGIN
            if (!email.isEmpty() &&
                !password.isEmpty() &&
                userService.isLoginUserEmail(email)) {
                if (userService.isLoginUserPassword(email, password)) {
                    return "redirect:/reaction_game";
                } else {
                    bindingResult.rejectValue("password", "error.password", "Неверный пароль");
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
            } else {
                if (!email.isEmpty() &&
                    !userService.isLoginUserEmail(email)) {
                    bindingResult.rejectValue("email", "error.email", "Такого пользователя не существует");
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                } else {
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
            }
        }
            return "redirect:/reaction_game";
    }


    @GetMapping("/exit")
    public String exit() {
        setAuthUserId(0);
        return "redirect:/enterPage";
    }

    private RedirectAttributes addToredirectAttributes(BindingResult bindingResult,
                                                       RedirectAttributes redirectAttributes,
                                                       User user) {
        redirectAttributes.getFlashAttributes().clear();
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customer", bindingResult);
        redirectAttributes.addFlashAttribute("user", user);

        return redirectAttributes;
    }


}
