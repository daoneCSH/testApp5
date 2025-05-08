package com.springboot.testapp4.data.repository;

import com.springboot.testapp4.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);

    Optional<User> findByUid(String uid);
}
