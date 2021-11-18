package com.suai.trainersuai.controllers;

import com.suai.trainersuai.persistence.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidaotr implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "email", "error.email", "Введите Email");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.password", "Введите пароль");
        ValidationUtils.rejectIfEmpty(errors, "name", "error.name", "Введите фамилию");
        ValidationUtils.rejectIfEmpty(errors, "secondName", "error.secondName", "Введите имя");
//        ValidationUtils.invokeValidator();
    }
}
