package com.springboot.testapp4.data.dao;

import com.springboot.testapp4.data.entity.User;
import com.springboot.testapp4.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository repository;

    @Autowired
    public UserDaoImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User insertUser(User user) {
        return repository.save(user);
    }

    @Override
    public User selectUser(Long id) {
        Optional<User> op = repository.findById(id);
        return op.orElse(null);
    }

    @Override
    public boolean checkUser(String username, String password) {
        return false;
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<User> op = repository.findById(id);
        if (op.isPresent()) {
            User p = op.get();
            repository.delete(p);
        } else {
            throw new Exception();
        }
    }
}
