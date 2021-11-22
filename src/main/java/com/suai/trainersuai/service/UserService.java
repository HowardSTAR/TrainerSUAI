package com.suai.trainersuai.service;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.repositories.UserRepository;
import com.suai.trainersuai.repositories.UserRatingRepository;
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

            if (user.isNew()) {
//                save User
                em.persist(user);
                setAuthUserId(user.getId());
                return user;
            } else {
//                update User
                user.setPassword(getUserById(authUserId()).getPassword());
                return em.merge(user);
            }
    }

//    удалить???
    public Long getByEmail(String email) {
        User userGet = repository.findByEmail(email);
        setAuthUserId(userGet.getId());
        return userGet.getId();
    }

//    проверка пользователя по email - login
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

//    проверка пароля пользователя - login
    public boolean isLoginUserPassword(String email, String password) {
        User userGet = repository.findByEmail(email);
        if (userGet.getPassword().equals(password) ) {
            setAuthUserId(userGet.getId());
            return true;
        }
        return false;
    }


    public User getUserById(long id) {
        return repository.findById(id);
    }

}
