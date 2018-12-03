package com.po.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Period {

    LocalDate startDate;
    LocalDate endDate;

    public String toString(){
        return String.format("from: %s to: %s ", startDate.toString(),endDate.toString());
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
}
