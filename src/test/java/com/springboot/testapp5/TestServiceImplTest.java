package com.springboot.testapp5;

import com.springboot.testapp5.domain.User;
import com.springboot.testapp5.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceImplTest {
    @Autowired
    private TestService testService;

    @Test
    void testInsert() throws Exception {
//        User user = User.builder().uid("test5").password("1234").build();
//        // user 필드 설정
//        testService.insert(user);
    }
}

