package com.suai.trainersuai.service;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.repositories.UserRepository;
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

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User save(User user) {
            if (user.isNew()) {
//                save User
                em.persist(user);
                return user;
            } else {
//                update User
                try {
                    user.setPassword(getUserById(authUserId()).getPassword());

                    System.out.println("UPDATE: user = "+user.getPassword()+"  authUser = "+authUserId());

                    return em.merge(user);
                } catch (NullPointerException e) {
                    return null;
                }
            }
    }

    public User getByEmail(String email) {
        User userGet = repository.findByEmail(email);
        return userGet;
    }

    public User getUserById(long id) {
        return repository.findById(id);
    }

}
