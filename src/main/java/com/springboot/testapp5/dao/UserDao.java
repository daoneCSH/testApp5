package com.springboot.testapp5.dao;

import com.springboot.testapp5.domain.User;

public interface UserDao {
    Iterable<User> selectAll();
    User insertUser(User user) throws Exception;
    User selectUser(Long id);
    User findUserByKey(String key);
    boolean checkUser(String username, String password) throws Exception;
    void deleteUser(Long id) throws Exception;

}
