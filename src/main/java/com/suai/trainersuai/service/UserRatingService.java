package com.suai.trainersuai.service;

import com.suai.trainersuai.persistence.entities.User;
import com.suai.trainersuai.persistence.entities.UserRating;
import com.suai.trainersuai.persistence.repositories.UserRatingRepository;
import com.suai.trainersuai.persistence.to.UserToRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.suai.trainersuai.util.SecurityUtil.authUserId;

@Service
@Transactional(readOnly = true)
public class UserRatingService {

    @Autowired
    private UserRatingRepository repositoryResult;

    @Autowired
    private UserService serviceUser;

    @Transactional
    public UserRating saveResult(float result) {
        authUserId();
        int resultProcent = Math.round(result * 100);
        UserRating userResult = new UserRating(resultProcent, null, authUserId());
        return repositoryResult.save(userResult);
    }

    public List<UserToRating> getAllRating() {
        List<UserRating> users = repositoryResult.findAll();
        List<UserToRating> userRating = new ArrayList<>();
        Map<Long, Integer> map = getMapRating(users);

        System.out.println("MAP: "+map);

        User userTemp;
        UserRating userRatingTemp;

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            userTemp = serviceUser.getUserById(entry.getKey());
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

    private Map<Long, Integer> getMapRating(List<UserRating> users) {
        Map<Long, Integer> map = new HashMap<>();

        for (UserRating us : users) {
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

}
