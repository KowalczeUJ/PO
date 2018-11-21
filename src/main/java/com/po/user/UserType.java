package com.po.user;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@AllArgsConstructor
public enum UserType {

    ADMIN(0),
    RECEPTIONIST(1),
    BASIC(2);

    @Getter
    private final int id;

    private static final Map<Integer, UserType> USER_TYPE_MAP = Maps.uniqueIndex(
            Arrays.asList(UserType.values()),
            UserType::getId
    );

    public static UserType fromId(int typeId) {
        return Optional.ofNullable(USER_TYPE_MAP.get(typeId))
                .orElseThrow(() -> new IllegalArgumentException(
                        format("User type with id=%d is not known.", typeId))
                );
    }
}
