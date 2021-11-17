package com.suai.trainersuai.util;

public class SecurityUtil {

    private static long id = 1;

    public static long authUserId() { return  id;   }

    public static void setAuthUserId(long id) { SecurityUtil.id = id;   }

}
