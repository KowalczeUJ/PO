package com.po.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class UserDetailsData {

    int userId;
    String address;
    String city;
    String phoneNumber;
    LocalDate birthDate;

}
