package com.po.user.repository;

import com.po.SessionFactoryRule;
import com.po.db.user.UserType;
import com.po.user.User;
import com.po.user.UserData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryImplTest {

    private UserData userData;
    private int userId;
    private UserRepository userRepository;

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Before
    public void setup() {
        userRepository = new UserRepositoryImpl(sessionRule.getsessionFactory());

        userData = UserData.builder()
                .username("repoTest1")
                .password("password")
                .type(UserType.BASIC)
                .build();
        userId = userRepository.addBasicUser(userData);
    }

    @Test
    public void shouldAddHotelUser() {
        UserData userData= UserData.builder()
                .username("repoTest2")
                .password("password")
                .type(UserType.BASIC)
                .build();

        int userId = userRepository.addHotelUser(userData);

        User expected = new User(userId, userData.getUsername(), userData.getType(), true);

        List<User> users = userRepository.getAllUsers();
        assertThat(users)
                .hasSize(2)
                .contains(expected);
    }

    @Test
    public void shouldAddBasicUser() {
        User expected = new User(userId, userData.getUsername(), userData.getType(), false);

        List<User> users = userRepository.getAllUsers();
        assertThat(users)
                .hasSize(1)
                .containsExactly(expected);
    }

    @Test
    public void shouldDeleteUser() {
        userRepository.deleteUser(userId);

        List<User> users = userRepository.getAllUsers();
        assertThat(users).isEmpty();
    }

    @Test
    public void shouldCheckIfUserIsRegular() {
        boolean isRegular = userRepository.isRegular(userId);

        assertThat(isRegular).isFalse();
    }

    @Test
    public void shouldCheckIfUserWithGivenUsernameExists() {
        boolean exists = userRepository.usernameExists(userData.getUsername());

        assertThat(exists).isTrue();
    }

    @Test
    public void shouldCheckIfUserWithGivenIdExists() {
        boolean exists = userRepository.exists(userId);

        assertThat(exists).isTrue();
    }

    @Test
    public void shouldLoginUser() {
        User user = userRepository.loginUser(userData);

        assertThat(user.getUserId()).isPositive().isNotNull();
        assertThat(user.getUsername()).isEqualTo(userData.getUsername());
        assertThat(user.getType()).isEqualTo(userData.getType());
    }

    @Test
    public void shouldGetAllUsers() {
        List<User> users = userRepository.getAllUsers();

        User expected = new User(userId, userData.getUsername(), userData.getType(), false);

        assertThat(users)
                .hasSize(1)
                .containsExactly(expected);
    }

}