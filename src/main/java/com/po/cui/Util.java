package com.po.cui;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    static Scanner scanner = new Scanner(System.in);

    public static String getInput() throws CUIBackException{
        String str = scanner.next().trim();
        if (str.equals("@escape")){
            throw new CUIBackException();
        }
        return str;
    }

    public static List<String> stringToSubstrings(String input){
        return Arrays.asList(input.split(" "));
    }

    public static Integer strToInteger(String input){
        return Integer.parseInt(input);
    }

    public static Double strToDouble(String input){
        return Double.parseDouble(input);
    }

    public static Date strToDate(String input) throws java.text.ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd:mm:yyyy");
        return formatter.parse(input);

    }

    public static String dateToStr(Date input) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:mm:yyyy");
        return formatter.format(input);
    }

    public static void print(String str){
        System.out.println(str);
    }

    public static boolean checkRegex(String text, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        return m.find();
    }

}
