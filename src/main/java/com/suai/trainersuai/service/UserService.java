package com.suai.trainersuai.service;

import com.suai.trainersuai.persistence.entities.RegistrationUser;
import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.repositories.RegistartionRepository;
import com.suai.trainersuai.persistence.repositories.UserRepository;
import com.suai.trainersuai.persistence.to.UserToRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<UserToRating> getAllRating() {
        List<User> users = repositoryResult.findAll();
        List<UserToRating> userRating = new ArrayList<>();
        Map<Long, Integer> map = getMapRating(users);

        System.out.println("MAP: "+map);

        RegistrationUser userTemp;
        User userRatingTemp;

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            userTemp = getRegistrationUserById(entry.getKey());
            userRating.add(new UserToRating(
                    userTemp.getName(),
                    userTemp.getSecondName(),
                    userTemp.getThirdName(),
                    entry.getValue()
            ));
        }

        System.out.println("List: "+userRating);

        return userRating;
    }

    private Map<Long, Integer> getMapRating(List<User> users) {
        Map<Long, Integer> map = new HashMap<>();

        for (User us : users) {
            try {
                int value = map.get(us.getIdRegistration());
                System.out.println("value = " +value);
                if (value <= us.getStat()) {
                    map.put(us.getIdRegistration(), us.getStat());
                }
            } catch (NullPointerException e) {
                map.put(us.getIdRegistration(), us.getStat());
            }
        }
        return map;
    }

    public RegistrationUser getRegistrationUserById(long id) {
        return repository.findById(id);
    }


}
