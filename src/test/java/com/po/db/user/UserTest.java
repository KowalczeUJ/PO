package com.po.db.user;

import com.po.SessionFactoryRule;
import com.po.user.repository.UserRepository;
import com.po.user.repository.UserRepositoryImpl;
import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Test
    public void shouldPersistEntityWithFullDataSpecified() {
        Session session = sessionRule.getSession();

        User user = User.builder()
                .username("test")
                .password("test123")
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(user);
        sessionRule.commit();

        UserRepository userRepository = new UserRepositoryImpl(sessionRule.getsessionFactory());

        List<User> users = userRepository.getAllUsers();

        assertNotNull(users);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getId()).isNotNull().isPositive();
        assertThat(users.get(0).getUsername()).isEqualTo(user.getUsername());
        assertThat(users.get(0).getPassword()).isEqualTo(user.getPassword());
        assertThat(users.get(0).getIsRegular()).isEqualTo(user.getIsRegular());
        assertThat(users.get(0).getType()).isEqualTo(user.getType());
        assertThat(users.get(0).getCreatedOn()).isEqualTo(user.getCreatedOn());
    }

}
