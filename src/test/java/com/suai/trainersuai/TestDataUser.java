package com.suai.trainersuai;

import com.suai.trainersuai.model.User;

public class TestDataUser {

    public static final String EMAIL = "user1@mail.ru";
    public static final String EMAIL_ERROR = "3@4.5";
    public static final String EMAIL_SAVE_USER = "user3@mail.ru";
    public static  final String NAME_UPDATE = "User1 name UPDATE";
    public static final long ID1 = 1l;
    public static final long ID_SEQ = 1l;


    public static final long ID_ERROR = 10000;

    public static final User USER1 = new User(1, "User1 name", "User1 secondname", "User1 thirdname",
            "user1@mail.ru", "911-123-45-67", "info for user1", null, "123");
    public static final User USER_SAVE_EX = new User( "User3 name", "User3 secondname", "User3 thirdname",
            "user3@mail.ru", "911-123-45-67", "info for user3", null, "333");

    public static User USER_SAVE_NEW = new User("User3 name", "User3 secondname", "User3 thirdname",
            "user3@mail.ru", "911-123-45-67", "info for user3", null, "333");

    public static final User USER_UPDATE = new User(1, "User1 name UPDATE", "User1 secondname", "User1 thirdname",
            "user1@mail.ru", "911-123-45-67", "info for user1", null, "123");

}