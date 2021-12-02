package com.suai.trainersuai.service;

import com.suai.trainersuai.model.UserRating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static com.suai.trainersuai.TestDataRating.*;
import static com.suai.trainersuai.util.SecurityUtil.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:db/initDb.sql",
        "classpath:db/populetDb.sql"},
        config = @SqlConfig(encoding = "UTF-8"))
class UserRatingServiceTest {

    @Autowired
    UserRatingService service;

    @Test
    void saveResult() {
        setAuthUserId(USER_ID);
        UserRating ratingSave = service.saveResult(RESULT);
        System.out.println("Save = "+ratingSave);
        long newId = ratingSave.getId();
        RATING_SAVE.setId(newId);
        assertEquals(RATING_SAVE, ratingSave);
    }

    @Test
    void getAllMaxRating() {
        assertEquals(LIST, service.getAllMaxRating());
    }
}