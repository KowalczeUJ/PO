package com.po.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class ReservationData {

    private int userId;
    private boolean isRegular;
    private int roomId;
    private int persons;
    private LocalDate startDate;
    private LocalDate endDate;

}
