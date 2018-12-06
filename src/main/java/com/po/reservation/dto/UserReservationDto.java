package com.po.reservation.dto;

import com.po.db.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class UserReservationDto {

    int userId;

    String roomNumber;
    RoomType roomType;

    BigDecimal totalPrice;
    int persons;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime createdOn;

    public String toString(){
        String out = "";
        out = String.format("room number:%s, room type:%s, total price: %s, num of guests:%d, from:%s, to:%s, reserved on:%s",roomNumber,roomType,totalPrice.toString(),persons,startDate,endDate,createdOn);
        return out;
    }
}
