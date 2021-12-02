package com.suai.trainersuai;

import com.suai.trainersuai.model.User;
import com.suai.trainersuai.model.UserRating;
import com.suai.trainersuai.to.UserToRating;

import java.util.Arrays;
import java.util.List;

public class TestDataRating {

    public static final List<UserToRating> LIST = Arrays.asList(
            new UserToRating("User1 name", "User1 secondname", "User1 thirdname", 42),
            new UserToRating("User2 name", "User2 secondname", "User2 thirdname", 95));


    public static UserRating RATING_SAVE = new UserRating(97, null, 1l);

    public static final float RESULT = 0.9733f;
    public static final long USER_ID = 1l;


}
