package com.springboot.testapp4.data.dao;

import com.springboot.testapp4.data.entity.User;

public interface UserDao {
    User insertUser(User user);
    User selectUser(Long id);
    boolean checkUser(String username, String password);
    void deleteUser(Long id) throws Exception;

}
