package com.studymouse.studymouseserver.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(User.builder()
                .name("gobuk")
                .email("example@gmail.com")
                .role(Role.USER)
                .build());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @DisplayName("Email로 회원을 찾아서 Optional로 반환한다.")
    @ParameterizedTest
    @CsvSource({"example@gmail.com, true", "test@gmail.com, false"})
    void findByEmail(String email, boolean isPresent) {
        Optional<User> user = userRepository.findByEmail(email);

        assertThat(user.isPresent()).isEqualTo(isPresent);
    }
}