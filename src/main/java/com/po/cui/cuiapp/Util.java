package com.po.cui.cuiapp;

import com.po.common.Period;
import com.po.db.user.UserType;
import com.po.user.UserData;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public  class Util {
    static UserData getUserData(){
        System.out.println("Please provide username:");
        String username = State.scanner.next().trim();
        System.out.println("Please provide user password:");
        String pass = State.scanner.next().trim();
        System.out.println("Please provide user type (number): 0:Admin,1:Receptionist,2:Basic ");
        int type = State.scanner.nextInt();
        if(type < 0 || type >2){
            System.out.println("Number not within range");
            return null;
        }
        UserType ut = UserType.values()[type];
        UserData ud = new UserData(username,pass,ut);

        return ud;
    }

    public static LocalDate getDate(){
        LocalDate date = null;
        try{
            String input = State.scanner.next().trim();
            SimpleDateFormat formatter = new SimpleDateFormat("dd:mm:yyyy");
            Date dt = formatter.parse(input);
            date = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(java.text.ParseException e){
            System.out.println("The input cannot be parsed to date.");
        }
        return date;
    }

    public static Period getPeriod(){
        System.out.println("Please provide start date in format of: DD:MM:YYYY");
        LocalDate date1 = getDate();
        if(date1==null){
            return null;
        }
        System.out.println("Please provide end date in format of: DD:MM:YYYY");
        LocalDate date2 = getDate();
        if(date1==null){
            return null;
        }
        if(date1.isAfter(date2)){
            System.out.println("Start date is later than end date. Aborting");
            return null;
        }
        return new Period(date1,date2);
    }

    public static Integer getInt(Integer min, Integer max){
        int i = State.scanner.nextInt();
        if (i < min || i > max) {
            System.out.println(String.format("%d must be in range: %d to %d",i,min,max));
            return null;
        }
        return i;
    }
}
