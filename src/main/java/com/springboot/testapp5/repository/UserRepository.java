package com.springboot.testapp5.repository;

import com.springboot.testapp5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    // uid 필드에 대한 존재 여부 확인 메소드 추가
    boolean existsByUid(String uid);

}
