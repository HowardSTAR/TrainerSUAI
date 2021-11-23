package com.suai.trainersuai.controllers;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.service.UserService;
import com.suai.trainersuai.validators.UserValidator;
import com.suai.trainersuai.validators.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.suai.trainersuai.util.SecurityUtil.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private Validation validation;

    @Autowired
    private UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @GetMapping("/enterPage")
    public String enterPageLoad(@ModelAttribute User user) {

        return "enterPage";
    }

    @PostMapping("/enterPage")
    public String registrationEterPage(@ModelAttribute @Valid User user,
                                       BindingResult bindingResult,
                                       @RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("action") String action,
                                       final RedirectAttributes redirectAttributes) {

//        REGISTRATION
        if (action.equals("isRegistration")) {
            try {
                if (bindingResult.hasErrors()) {
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
                setAuthUserId(userService.save(user).getId());
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
                validation.isLoginUserEmail(email)) {
                if (validation.isLoginUserPassword(email, password)) {
                    setAuthUserId(userService.getByEmail(email).getId());
                    return "redirect:/secondChance";
                } else {
                    bindingResult.rejectValue("password", "error.password", "Неверный пароль");
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
            } else {
                if (!email.isEmpty() &&
                    !validation.isLoginUserEmail(email)) {
                    bindingResult.rejectValue("email", "error.email", "Такого пользователя не существует");
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                } else {
                    addToredirectAttributes(bindingResult, redirectAttributes, user);
                    return "enterPage";
                }
            }
        }
            return "redirect:/secondChance";
    }

//  Выход из профиля
    @GetMapping("/exit")
    public String exit() {
        setAuthUserId(0);
        return "redirect:/enterPage";
    }

//    для validation
    private RedirectAttributes addToredirectAttributes(BindingResult bindingResult,
                                                       RedirectAttributes redirectAttributes,
                                                       User user) {
        redirectAttributes.getFlashAttributes().clear();
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customer", bindingResult);
        redirectAttributes.addFlashAttribute("user", user);
        return redirectAttributes;
    }


}
