package com.suai.trainersuai;

import com.suai.trainersuai.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class TesetDataRegistrationController {

    public static final String REGISTRATION = "isRegistration";

    public static User USER_SAVE_NEW = new User("User3 name", "User3 secondname", "User3 thirdname",
            "user3@mail.ru", "911-123-45-67", "info for user3", null, "333");

    public static final RedirectAttributes REDIRECT_ATTRIBUTES = null;



}
