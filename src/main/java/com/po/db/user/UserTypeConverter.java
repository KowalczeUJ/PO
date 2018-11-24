package com.po.db.user;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserType userType) {
        return Optional.ofNullable(userType)
                .map(UserType::getId)
                .orElse(null);
    }

    @Override
    public UserType convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(UserType::fromId)
                .orElse(null);
    }
}
