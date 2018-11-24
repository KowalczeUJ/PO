package com.po.cui;

import java.util.Date;
import java.util.Set;
import java.util.Date;

/**
 * Interface for simple command line user interface
 * <p>
 * Doensn't use any ncurses etc. Provides functionality for
 * Provides functionalities for simple list choices, datetime parsing and range, etc.
 * Generally functions handle wrong input and give user a chance to retry
 *
 * The CUIBackException is thrown when user presses [ESC] key
 *
 * When users insert wrong data, the functions print out the requirements (like 'Please insert integer in range X to Y)
 * For more complex input parsing, just parse the input on your own.
 *
 * The "choose" functions require a set of data from which the user can pick one value
 * The "provide" functions read a single input from user, given some limiting conditions
 * The "print" function is a shortcut to System.out.println()
 *
 * dates will only return with precision to day.
 *
 * </p>
 */
public interface cui {
    Provider p = new Provider();
    Chooser c = new Chooser();
    Util u = new Util();
    /**
     * Returns String chosen by user from given set.
     * @param  choices Set consisting of possible strings.
     * @return Set of chosen strings
     */
    public static String chooseString(Set<String> choices) throws CUIBackException {
        return c.chooseString(choices);
    };
    /**
     * Returns Integer chosen by user from given set.
     * @param  choices Set consisting of possible integers.
     * @return Set of chosen integers
     */
    public static Integer chooseInteger(Set<Integer> choices) throws CUIBackException {
        return c.chooseInteger(choices);
    };
    /**
     * Returns Double chosen by user from given set.
     * @param  choices Set consisting of possible doubles.
     * @return Set of chosen doubles
     */
    public static Double chooseDouble(Set<Double> choices) throws CUIBackException {
        return c.chooseDouble(choices);
    };
    /**
     * Returns Date chosen by user from given set.
     * @param  choices Set consisting of possible dates.
     * @return Set of chosen dates
     */
    public static Date chooseDate(Set<Date> choices) throws CUIBackException {
        return c.chooseDate(choices);
    };

    /**
     * Returns Integer given by the user
     * @return Given integer
     */
    public static Integer provideInteger() throws CUIBackException {
        return p.provideInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    /**
     * Returns Integer given by user.
     * @param  min Minimal value of given integer, the given integer must be less or equal to it.
     * @param  max Maximal value of given integer, the given integer must be greater or equal to it.
     * @return given integer
     */
    public static Integer provideInteger(Integer min, Integer max) throws CUIBackException  {
        return p.provideInteger(min,max);
    }
    /**
     * Returns String given by the user
     * @return Given string
     */
    public static String provideString() throws CUIBackException {
        return p.provideString("*");
    };
    /**
     * Returns String given by user.
     * @param  regex regular expression that will be used to validate the given string.
     * @return Given string
     */
    public static String provideString(String regex) throws CUIBackException {
        return p.provideString(regex);
    };
    /**
     * Returns Double given by the user
     * @return Given double
     */
    public static Double provideDouble() throws CUIBackException {
      return p.provideDouble(Double.MIN_VALUE,Double.MAX_VALUE);
    };
    /**
     * Returns Double given by user.
     * @param  min Minimal value of given double, the given double must be less or equal to it.
     * @param  max Maximal value of given double, the given double must be greater or equal to it.
     * @return Given double
     */
    public static Double provideDouble(Double min, Double max) throws CUIBackException {
        return p.provideDouble(min, max);
    }
    /**
     * Returns Date given by the user
     * @return Given date
     */
    public static Date provideDate() throws CUIBackException {
        return p.provideDate(new Date(Long.MIN_VALUE),new Date(Long.MAX_VALUE));
    }
    /**
     * Returns Double given by user.
     * @param  from date, that will be used to validate the user input, so that given date must be greater or equal to it.
     * @param  to date, that will be used to validate the user input, so that given date must be lesser or equal to it.
     * @return Given double
     */
    public static Date provideDate(Date from, Date to) throws CUIBackException {
        return p.provideDate(from,to);
    }

    /**
     * Shortcut to System.out.println(). Also may be used sometime later
     * @param text printed text
     */
    public static void print(String text){
        u.print(text);
    };

}
