package com.cmed.util;

import java.time.LocalDate;
import java.util.Date;

public class DateUtil {

    public static String convertToString(Date date) {
        try {
            return date.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date convertToDate(String date) {
        try {
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date currentDate() {
        return java.sql.Date.valueOf(LocalDate.now());
    }
}
