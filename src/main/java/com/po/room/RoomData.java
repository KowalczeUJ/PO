package com.po.room;

import com.po.db.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class RoomData {

    int id;
    RoomType type;
    BigDecimal pricePerNight;

}
