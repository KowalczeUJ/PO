package com.po.user.repository;

import com.po.SessionFactoryRule;
import com.po.db.user.User;
import com.po.db.user.UserDetails;
import com.po.db.user.UserType;
import com.po.user.UserDetailsData;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsRepositoryImplTest {

    private User USER;
    private UserDetailsRepository repository;

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Before
    public void setup() {
        repository = new UserDetailsRepositoryImpl(sessionRule.getsessionFactory());
        Session session = sessionRule.getSession();

        USER = User.builder()
                .id(1)
                .username("test")
                .password("test123")
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(USER);
        sessionRule.commit();
    }

    @Test
    public void shouldAddAndThenFetchUserDetails() {
        UserDetails userDetails = UserDetails.builder()
                .user(USER)
                .address("Flower Street 132/12")
                .city("Phoenix")
                .phoneNumber("938210294")
                .birthDate(LocalDate.of(1989, 11, 30))
                .build();

        UserDetailsData expected = UserDetailsData.builder()
                .userId(USER.getId())
                .address(userDetails.getAddress())
                .city(userDetails.getCity())
                .phoneNumber(userDetails.getPhoneNumber())
                .birthDate(userDetails.getBirthDate())
                .build();

        repository.addUserDetails(userDetails);
        UserDetailsData userDetailsData = repository.getUserDetails(USER.getId());

        assertThat(userDetailsData).isEqualTo(expected);
    }

}