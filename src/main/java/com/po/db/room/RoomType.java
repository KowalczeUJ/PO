package com.po.db.room;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@AllArgsConstructor
public enum RoomType {

    STUDIO(0, 1),
    DELUXE(1, 1.2),
    PREMIER(2, 1.5),
    PENTHOUSE(3, 2.0);

    @Getter
    private final int id;

    @Getter
    private final double multiplier;

    private static final Map<Integer, RoomType> ROOM_TYPE_MAP = Maps.uniqueIndex(
            Arrays.asList(RoomType.values()),
            RoomType::getId
    );

    public static RoomType fromId(int typeId) {
        return Optional.ofNullable(ROOM_TYPE_MAP.get(typeId))
                .orElseThrow(() -> new IllegalArgumentException(
                        format("Room type with id=%d is not known.", typeId)
                ));
    }

}
