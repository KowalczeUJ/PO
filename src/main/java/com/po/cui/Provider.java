package com.po.cui;

import java.util.Date;

public class Provider {
    public Integer provideInteger(Integer min, Integer max) throws CUIBackException{
        while(true){
            String str = Util.getInput();
            try{
                Integer num =  Util.strToInteger(str);
                if (num < min || num > max) {
                    Util.print(String.format("%i must be in range: %i to %i, please try again",num,min,max));
                    continue;
                } else {
                    return num;
                }
            }catch(Exception e){
                Util.print(String.format("%s is not an integer, please try again",str));
                continue;
            }
        }
    }

    public Double provideDouble(Double min, Double max) throws CUIBackException{
        while(true){
            String str = Util.getInput();
            try{
                Double num =  Util.strToDouble(str);
                if (num < min || num > max) {
                    Util.print(String.format("%d must be in range: %d to %d, please try again",num,min,max));
                    continue;
                } else {
                    return num;
                }
            }catch(Exception e){
                Util.print(String.format("%s is not a double, please try again",str));
                continue;
            }
        }
    }


    public Date provideDate(Date from, Date to) throws CUIBackException{
        while(true){
            String str = Util.getInput();
            try{
                Date date =  Util.strToDate(str);
                if (date.before(from)  || date.after(to)) {
                    Util.print(String.format("%s must be in range: %s to %s, please try again",Util.dateToStr(date),Util.dateToStr(from),Util.dateToStr(to)));
                    continue;
                } else {
                    return date;
                }
            }catch(Exception e){
                Util.print(String.format("%s is not a properly formatted date, please try again, using format DD:MM:YYYY",str));
                continue;
            }
        }
    }

    public String provideString(String regex) throws CUIBackException{
        while(true){
            String str = Util.getInput();
            if (!Util.checkRegex(str,regex)) {
                Util.print(String.format("%s is not a proper input.",str));
                continue;
            } else {
                return str;
            }

        }
    }

}
