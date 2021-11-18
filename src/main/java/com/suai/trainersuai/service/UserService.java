package com.suai.trainersuai.service;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.entities.UserRating;
import com.suai.trainersuai.persistence.repositories.UserRepository;
import com.suai.trainersuai.persistence.repositories.UserRatingRepository;
import com.suai.trainersuai.persistence.to.UserToRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.suai.trainersuai.util.SecurityUtil.*;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRatingRepository repositoryResult;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User save(User user) {

        System.out.println("User in service: "+user);

            if (user.isNew()) {
                em.persist(user);
                setAuthUserId(user.getId());
                return user;

            } else {
                user.setPassword(getUserById(authUserId()).getPassword());

                System.out.println("User in service set password: "+user);

                return em.merge(user);
            }
    }

    public Long getByEmail(String email) {
        User userGet = repository.findByEmail(email);
        setAuthUserId(userGet.getId());
        return userGet.getId();
    }

    public boolean isLoginUser(User user) {

        try {
            User userGet = repository.findByEmail(user.getEmail());
            if (userGet.getPassword().equals(user.getPassword()) &&
                userGet.getEmail().equals(user.getEmail())) {
                setAuthUserId(userGet.getId());
                return true;
            }
            return false;

        } catch (NullPointerException e) {
            System.out.println("Нет такого пользователя");
            return false;
        }

    }


    public User getUserById(long id) {

        return repository.findById(id);
    }



}
