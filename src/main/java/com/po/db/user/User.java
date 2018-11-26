package com.po.db.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name ="password", length = 50, nullable = false)
    private String password;

    @Column(name = "type", columnDefinition = "TINYINT(4)")
    @ColumnDefault("'2'")
    @Convert(converter = UserTypeConverter.class)
    private UserType type = UserType.BASIC;

    @Column(name = "is_regular", columnDefinition = "TINYINT(1)")
    @ColumnDefault("'0'")
    private Boolean isRegular;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.type = builder.type;
        this.isRegular = builder.isRegular;
        this.createdOn = builder.createdOn;
    }

    public static class UserBuilder{

        private int id;
        private String username;
        private String password;
        private UserType type;
        private Boolean isRegular;
        private LocalDateTime createdOn;

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }


        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder type(UserType userType) {
            this.type = userType;
            return this;
        }

        public UserBuilder isRegular(Boolean isRegular) {
            this.isRegular = isRegular;
            return this;
        }

        public UserBuilder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
