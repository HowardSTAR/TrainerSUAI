package com.suai.trainersuai.service;

import com.suai.trainersuai.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static com.suai.trainersuai.TestDataUser.*;

import static com.suai.trainersuai.util.SecurityUtil.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:db/initDb.sql",
                "classpath:db/populetDb.sql"},
                config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void getUserByEmailTest() {
        User userActual = service.getByEmail(EMAIL);
        assertEquals(userActual, USER1);
    }

    @Test
    public void notFoundUserByEmail() {
        assertNull(service.getByEmail(EMAIL_ERROR));
    }

    @Test
    public void getUserById() {
        User userActual = service.getUserById(ID1);
        assertEquals(userActual, USER1);
    }

    @Test
    public void notFoundUserById() {
        assertNull(service.getUserById(ID_ERROR));
    }

    @Test
    public void saveUser() {
        User userSave = service.save(USER_SAVE_NEW);
        long newId = userSave.getId();
        USER_SAVE_NEW.setId(newId);
        assertEquals(userSave, USER_SAVE_NEW);
        assertEquals(service.getUserById(newId), USER_SAVE_NEW);
    }


    @Test
    public void updateUser() {
        setAuthUserId(ID_SEQ);
        User userUpdate = service.getUserById(ID1);
        userUpdate.setName(NAME_UPDATE);
        assertEquals(service.save(userUpdate), USER_UPDATE);
    }

    @Test
    public void updateUserAnotherId() {
        setAuthUserId(ID_ERROR);
        User userUpdate = service.getUserById(ID1);
        System.out.println("user = "+userUpdate);
        System.out.println("authID = "+authUserId());
        assertNull(service.save(userUpdate));
    }
}
