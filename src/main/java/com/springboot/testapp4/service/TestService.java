package com.springboot.testapp4.service;

import com.springboot.testapp4.data.entity.User;

import java.util.Optional;

/** test 서비스 : Service  */
public interface TestService {
    /** 모든 데이터 가져오기 */
    Iterable<User> selectAll();

    /** id로 데이터 가져오기 */
    Optional<User> selectById(long id);

    /** key로 id 가져오기 */
    long selectByKey(String key);

    /** 계정 정보와 암호 확인 */
    boolean checkAccountPassword(String myAccount, String myPassword) throws Exception;

    /** 계정 등록 */
    void insert(User data) throws Exception;

    /** 계정 변경 */
    void update(User data);

    /** 계정 삭제 */
    void delete(long id);

    void setDB(String dbKey);
}
