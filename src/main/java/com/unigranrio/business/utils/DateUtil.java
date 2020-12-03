package com.unigranrio.business.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static boolean isWeekDay(Date date) {

        LocalDate local = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return local.getDayOfWeek() != DayOfWeek.SATURDAY && local.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    public static boolean isWeekendDay(Date date) {

        LocalDate local = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return local.getDayOfWeek() == DayOfWeek.SATURDAY || local.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static Date incrementDate(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }
}
