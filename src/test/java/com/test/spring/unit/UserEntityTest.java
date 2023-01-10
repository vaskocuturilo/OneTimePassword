package com.test.spring.unit;


import com.test.spring.entity.UserEntity;
import com.test.spring.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepo userRepo;


    @Test
    public void testUserEntity() {
        UserEntity userEntity = new UserEntity();
        String randomData = createDataForTesting();
        userEntity.setEmail(randomData);
        userEntity.setPassword(randomData);
        userEntity.setFirstName(randomData);
        userEntity.setLastName(randomData);

        UserEntity saveUserEntity = userRepo.save(userEntity);

        UserEntity existUserEntity = testEntityManager.find(UserEntity.class, saveUserEntity.getUserId());

        assertThat(userEntity.getEmail()).isEqualTo(existUserEntity.getEmail());

    }

    private String createDataForTesting() {
        return "test_" + new Date().getTime() + "@qa.team";
    }
}
