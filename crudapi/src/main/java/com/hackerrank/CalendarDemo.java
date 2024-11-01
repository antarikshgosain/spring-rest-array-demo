package com.hackerrank;

import java.util.Calendar;

public class CalendarDemo {
    public static String findDay(int month, int day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK);

        String[] days = {"SUNDAY","MONDAY","TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY","SATURDAY"};

        return days[dayNum - 1];

    }

    public static void main(String[] args) {
        findDay(8,4,2015);
    }
}
