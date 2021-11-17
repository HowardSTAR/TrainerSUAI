package com.suai.trainersuai.service;

import com.suai.trainersuai.persistence.entities.RegistrationUser;
import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.repositories.RegistartionRepository;
import com.suai.trainersuai.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.suai.trainersuai.util.SecurityUtil.*;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RegistartionRepository repository;

    @Autowired
    private UserRepository repositoryResult;

    @Transactional
    public RegistrationUser save(RegistrationUser user) {
        RegistrationUser userSave = repository.save(user);
        setAuthUserId(userSave.getId());
        return userSave;
    }

    public Long getByEmail(String email) {
        RegistrationUser userGet = repository.findByEmail(email);
        setAuthUserId(userGet.getId());
        return userGet.getId();
    }

    @Transactional
    public User saveResult(float result) {
        authUserId();
        int resultProcent = Math.round(result * 100);
        User userResult = new User(resultProcent, null, authUserId());
        return repositoryResult.save(userResult);
    }

    public boolean isLoginUser(RegistrationUser user) {

        try {
            RegistrationUser userGet = repository.findByEmail(user.getEmail());
            if (userGet.getPassword().equals(user.getPassword())) {
                setAuthUserId(userGet.getId());
                return true;
            }
            return false;

        } catch (NullPointerException e) {
            System.out.println("Нет такого пользователя");
            return false;
        }

    }


}
