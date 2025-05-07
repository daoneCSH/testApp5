package com.springboot.testapp4.commons;

import com.springboot.testapp4.data.entity.User;

public class DebugLog {
    /**
     * 기본적인 디버그
     * @param message
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * 클래스명 + 메시지
     * @param func
     * @param message
     */
    public static void log(String func, Object message) {
        System.out.println(func + "|" + message.toString());
    }

    public static void log(String func, Iterable<User> message) {
        for (User user : message) {
            System.out.println(func + "|" + user.toString());
        }

    }
}


