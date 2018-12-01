package com.po.db.user;

import com.po.SessionFactoryRule;
import com.po.user.UserDetailsData;
import com.po.user.repository.UserDetailsRepository;
import com.po.user.repository.UserDetailsRepositoryImpl;
import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsTest {

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Test
    public void shouldPersistEntityWithFullDataSpecified() {
        Session session = sessionRule.getSession();

        User user = User.builder()
                .id(1)
                .username("test")
                .password("test123")
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(user);

        UserDetails userDetails = UserDetails.builder()
                .user(user)
                .address("Manning Street 12/2")
                .city("Denver")
                .phoneNumber("129310249")
                .birthDate(LocalDate.of(1985, 12, 24))
                .build();

        session.save(userDetails);
        sessionRule.commit();

        UserDetailsRepository repository = new UserDetailsRepositoryImpl(sessionRule.getsessionFactory());

        UserDetailsData userDetailsData = repository.getUserDetails(userDetails.getUser().getId());

        assertThat(userDetailsData.getUserId()).isNotNull().isPositive();
        assertThat(userDetailsData.getAddress()).isEqualTo(userDetails.getAddress());
        assertThat(userDetailsData.getCity()).isEqualTo(userDetails.getCity());
        assertThat(userDetailsData.getPhoneNumber()).isEqualTo(userDetails.getPhoneNumber());
        assertThat(userDetailsData.getBirthDate()).isEqualTo(userDetails.getBirthDate());
    }

}
