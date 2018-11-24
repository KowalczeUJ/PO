package com.po.room;

import com.po.db.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
@Builder
public class RoomView {

    int floor;
    int beds;
    RoomType type;
    BigDecimal pricePerNight;

}
