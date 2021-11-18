package com.suai.trainersuai.service;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.repositories.UserRepository;
import com.suai.trainersuai.persistence.repositories.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public boolean isLoginUserEmail(String email) {

        try {
            if (repository.findByEmail(email).getEmail().equals(email)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isLoginUserPassword(String email, String password) {
        User userGet = repository.findByEmail(email);
        if (userGet.getPassword().equals(password) ) {
//                &&
//                userGet.getEmail().equals(email)) {
            setAuthUserId(userGet.getId());
            return true;
        }
        return false;
    }


    public User getUserById(long id) {

        return repository.findById(id);
    }

    @Transactional
    public void deleteUser22() {
        repository.deleteById(22);
    }


}
