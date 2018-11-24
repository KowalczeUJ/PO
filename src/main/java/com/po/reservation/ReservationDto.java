package com.po.reservation;


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
public class ReservationDto {

    int userId;
    String username;
    Boolean isRegular;

    int roomId;
    String roomNumber;
    RoomType roomType;

    BigDecimal totalPrice;
    int persons;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime createdOn;


}
