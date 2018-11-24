package com.po.db.room;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class RoomTypeConverter implements AttributeConverter<RoomType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoomType roomType) {
        return Optional.ofNullable(roomType)
                .map(RoomType::getId)
                .orElse(null);
    }

    @Override
    public RoomType convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(RoomType::fromId)
                .orElse(null);
    }

}
