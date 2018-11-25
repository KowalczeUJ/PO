package com.po.reservation;

import com.po.room.RoomData;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class TotalCost {

    private final long days;
    private final double multiplier;
    private final BigDecimal pricePerNight;

    @Getter
    private BigDecimal totalCost;

    TotalCost(long days, RoomData roomData) {
        this.days = days;
        this.pricePerNight = roomData.getPricePerNight();
        this.multiplier = roomData.getType().getMultiplier();
    }

    TotalCost getBaseCost() {
        this.totalCost = pricePerNight
                .multiply(BigDecimal.valueOf(multiplier))
                .multiply(BigDecimal.valueOf(days));

        return this;
    }

    TotalCost getRegularUserDiscount() {
        if (totalCost != null) {
            totalCost = totalCost.multiply(BigDecimal.valueOf(0.9));
        }

        return this;
    }

    TotalCost getUpFrontDiscount(LocalDate startDate) {
        LocalDate now = LocalDate.now();
        long daysInAdvance = DAYS.between(now, startDate);

        BigDecimal discount = BigDecimal.valueOf(100)
                .subtract(BigDecimal.valueOf(daysInAdvance * 0.02));

        totalCost = totalCost
                .multiply(discount)
                .divide(BigDecimal.valueOf(100.0), 2, BigDecimal.ROUND_HALF_DOWN);

        return this;
    }

}
