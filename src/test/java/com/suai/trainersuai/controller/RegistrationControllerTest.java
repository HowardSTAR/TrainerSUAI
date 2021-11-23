package com.suai.trainersuai.controller;

import com.suai.trainersuai.controllers.RegistrationController;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;

import static com.suai.trainersuai.TesetDataRegistrationController.*;

class RegistrationControllerTest {

    @Test
    void enterPageLoad() {
    }

    @Test
    void registrationEterPage() {
        RegistrationController registrationController = new RegistrationController();
//        String result = registrationController.enterPageLoad(USER_SAVE_NEW);
        String result = registrationController.registrationEterPage(USER_SAVE_NEW,
                null, "user1@mail.ru", "123", REGISTRATION, REDIRECT_ATTRIBUTES);
        System.out.println("result = " + result);
    }

    @Test
    void exit() {
            RegistrationController registrationController = new RegistrationController();
            String result = registrationController.exit();
            System.out.println("result = "+result);
            assertEquals(result, "redirect:/enterPage");
    }
}