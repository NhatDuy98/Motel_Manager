package caseStudy.option.format;

import java.util.regex.Pattern;

public class Format {
    private static final String PHONE_NUMBER = "^[0]\\d{9}$";
    private static final String AGE = "^\\d{1,2}$";
    private static final String ADDRESS = "";
    private static final String CCCD="";

    public static boolean checkPhoneNumber(String phoneNumber){
        return Pattern.compile(PHONE_NUMBER).matcher(phoneNumber).matches();
    }
    public static boolean checkAge(String age){
        return Pattern.compile(AGE).matcher(age).matches();
    }
}
