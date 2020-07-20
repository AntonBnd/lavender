package by.lavender.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String CARD_NUMBER = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$";
    private static final String NAME = "^([A-Za-z]{2,40}|[а-яА-ЯЁё]{2,40})$";
    private static final String PHONE_NUMBER = "(\\+375)(25|29|33|44)([1-9]){1}([0-9]){6}$";
    private static final String USERNAME = "^(([a-zA-Z]{1})([a-zA-Z0-9]){2,18}([a-zA-z]))$";
    private static final String PASSWORD = "^([a-zA-Z0-9]{4,20})$";
    private static final String DATE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
    private static final String PRICE = "^([1-9][0-9]*(.[0-9]{1,2})?|[0])$";
    private static final String ITEM_NUMBER = "^([1]{1}|[1-9]{1}|[0-9]{1,3})$";

    public static boolean validateCardNumber(String str) {
        return check(str, CARD_NUMBER);
    }

    public static boolean validateName(String str) {
        return check(str, NAME);
    }

    public static boolean validatePhoneNumber(String str) {
        return check(str, PHONE_NUMBER);
    }

    public static boolean validateUsername(String str) {
        return check(str, USERNAME);
    }

    public static boolean validatePassword(String str) {
        return check(str, PASSWORD);
    }

    public static boolean validateCost(String str) {
        return check(str, PRICE);
    }

    public static boolean validateItemNumber(String str) {
        return check(str, ITEM_NUMBER);
    }

    public static boolean validateDate(String startDate, String endDate) {
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date currentDate = java.sql.Date.valueOf(modifiedDate);
        if (!check(startDate, DATE) && !check(endDate, DATE)) {
            return false;
        }
        Date beginDate = java.sql.Date.valueOf(startDate);
        Date eDate = java.sql.Date.valueOf(endDate);
        if (eDate.compareTo(beginDate) >= 0 && beginDate.compareTo(currentDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean check(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}