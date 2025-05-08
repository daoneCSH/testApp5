package com.springboot.testapp4.data.dao;

import com.springboot.testapp4.commons.crypo.PacketCrypto;
import com.springboot.testapp4.data.entity.User;
import com.springboot.testapp4.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserDaoImpl implements UserDao {
    static final String keyString = "04a759baaa5e66df";

    private final UserRepository repository;

    @Autowired
    public UserDaoImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> selectAll() {
        return repository.findAll();
    }

    @Override
    public User insertUser(User user) throws Exception {
        user.setPassword(PacketCrypto.encryptEncodeBase64(user.getPassword().getBytes(),keyString));
        return repository.save(user);
    }

    @Override
    public User selectUser(Long id) {
        Optional<User> op = repository.findById(id);
        return op.orElse(null);
    }

    @Override
    public User findUserByKey(String key) {
        Optional<User> op = repository.findByUid(key);
        return op.orElse(null);
    }

    @Override
    public boolean checkUser(String username, String myPassword) throws Exception {
        boolean check = false;
        Optional<User> u = repository.findByUid(username);
        String dep = new String(PacketCrypto.decryptDecodeBase64(u.get().getPassword(),keyString));
        log.info("checkAccountPassword input:{} Decode:{}", username, dep);
        if(dep.equals(myPassword)) {
            check = true;
        }
        return check;
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
