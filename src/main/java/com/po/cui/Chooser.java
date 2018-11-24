package com.po.cui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Chooser {




    public static String chooseString(Set<String> input) throws CUIBackException {
        List<String> list = new ArrayList<String>();
        list.addAll(input);

        int len = list.size();
        Util.print("Please choose one of following, by typing the number:");
        for (int i =0; i < len; i++){
            Util.print(String.format("%i : %s",i,list.get(i)));
        }
        while(true){
            String str = Util.getInput();
            try{
                Integer num =  Util.strToInteger(str);
                if (num < 0 || num >= len) {
                    Util.print(String.format("%i must be in range: 0 to %i, please try again",num,len-1));
                    continue;
                } else {
                    return list.get(num);
                }
            }catch(Exception e){
                Util.print(String.format("%s is not an integer, please try again",str));
                continue;
            }
        }
    }

    public static Double chooseDouble(Set<Double> input) throws CUIBackException {


        List<Double> list = new ArrayList<Double>();

        list.addAll(input);

        int len = list.size();
        Util.print("Please choose one of following, by typing the number:");
        for (int i =0; i < len; i++){
            Util.print(String.format("%i : %d",i,list.get(i)));
        }
        while(true){
            String str = Util.getInput();
            try{
                Integer num =  Util.strToInteger(str);
                if (num < 0 || num >= len) {
                    Util.print(String.format("%i must be in range: 0 to %i, please try again",num,len-1));
                    continue;
                } else {
                    return list.get(num);
                }
            }catch(Exception e){
                Util.print(String.format("%s is not an integer, please try again",str));
                continue;
            }
        }
    }

    public static Integer chooseInteger(Set<Integer> input) throws CUIBackException {


        List<Integer> list = new ArrayList<Integer>();

        list.addAll(input);

        int len = list.size();
        Util.print("Please choose one of following, by typing the number:");
        for (int i =0; i < len; i++){
            Util.print(String.format("%i : %i",i,list.get(i)));
        }
        while(true){
            String str = Util.getInput();
            try{
                Integer num =  Util.strToInteger(str);
                if (num < 0 || num >= len) {
                    Util.print(String.format("%i must be in range: 0 to %i, please try again",num,len-1));
                    continue;
                } else {
                    return list.get(num);
                }
            }catch(Exception e){
                Util.print(String.format("%s is not an integer, please try again",str));
                continue;
            }
        }
    }
    public static Date chooseDate(Set<Date> input) throws CUIBackException {


        List<Date> list = new ArrayList<Date>();

        list.addAll(input);

        int len = list.size();
        Util.print("Please choose one of following, by typing the number:");
        for (int i =0; i < len; i++){
            Util.print(String.format("%i : %s",i,Util.dateToStr(list.get(i))));
        }
        while(true){
            String str = Util.getInput();
            try{
                Integer num =  Util.strToInteger(str);
                if (num < 0 || num >= len) {
                    Util.print(String.format("%i must be in range: 0 to %i, please try again",num,len-1));
                    continue;
                } else {
                    return list.get(num);
                }
            }catch(Exception e){
                Util.print(String.format("%s is not an integer, please try again",str));
                continue;
            }
        }
    }
}
