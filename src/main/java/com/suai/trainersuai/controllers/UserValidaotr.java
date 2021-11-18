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

        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
    }
}
