package com.springboot.testapp4.service;

import com.springboot.testapp4.commons.DebugLog;
import com.springboot.testapp4.commons.crypo.PacketCrypto;
import com.springboot.testapp4.data.entity.User;
import com.springboot.testapp4.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    static final String keyString = "04a759baaa5e66df";
    /** Repository: 인젝션 */
    @Autowired
    UserRepository repo;

    @Override
    public Iterable<User> selectAll() {
        return repo.findAll();
    }

    @Override
    public Optional<User> selectById(long id) {
        return repo.findById(id);
    }

    @Override
    public long selectByKey(String myAccount) {
        DebugLog.log("selectByKey", myAccount);
        User u = repo.getByUid(myAccount);
        DebugLog.log("selectByKey", u);
        return u.getId();
    }

    @Override
    public boolean checkAccountPassword(String myAccount, String myPassword) throws Exception {
        DebugLog.log("checkAccountPassword", myAccount + " " + myPassword);
        boolean check = false;
        User u = repo.getByUid(myAccount);
        DebugLog.log("checkAccountPassword", u);
        String dep = new String(PacketCrypto.decryptDecodeBase64(u.getPassword(),keyString));
        DebugLog.log("checkAccountPassword", "Decode:" + dep);
        if(dep.equals(myPassword)) {
            check = true;
        }
        return check;
    }

    @Override
    public void insert(User data) throws Exception{
        data.setPassword(PacketCrypto.encryptEncodeBase64(data.getPassword().getBytes(),keyString));
        repo.save(data);
    }

    @Override
    public void update(User data) {
        repo.save(data);
    }

    @Override
    public void delete(long id) {
        repo.deleteById(id);
    }
}
