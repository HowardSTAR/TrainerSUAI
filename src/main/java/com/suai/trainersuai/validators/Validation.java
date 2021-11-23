package com.suai.trainersuai.validators;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.suai.trainersuai.util.SecurityUtil.setAuthUserId;

@Component
public class Validation {

    @Autowired
    private UserService service;

    //    проверка пароля пользователя - login
    public boolean isLoginUserPassword(String email, String password) {
        User userGet = service.getByEmail(email);
        if (userGet.getPassword().equals(password) ) {
            setAuthUserId(userGet.getId());
            return true;
        }
        return false;
    }


    //    проверка пользователя по email - login
    public boolean isLoginUserEmail(String email) {

        try {
            if (service.getByEmail(email).getEmail().equals(email)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }


}
