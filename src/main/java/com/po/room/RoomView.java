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

    int id;
    String number;
    int floor;
    int beds;
    RoomType type;
    BigDecimal pricePerNight;

    public String toString(){
        return String.format("id: %d, number: %s, floor: %d, number of beds: %d, room type: %s, price per night: %f",id,number,floor,beds,type,pricePerNight);
    }
}
