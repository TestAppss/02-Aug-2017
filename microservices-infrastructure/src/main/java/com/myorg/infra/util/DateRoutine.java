package com.myorg.infra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateRoutine {

    private static final Logger LOGGER = Logger.getLogger(DateRoutine.class);

    private DateRoutine() {
        super();
    }

    public static String currentDateAsStr() {
        DateTime now = new DateTime();
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZoneUTC();
        return formatter.print(now);
    }

    public static String currentDateTimeAsStr() {
        DateTime now = new DateTime();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(now);
    }

    public static boolean validateDate(Date date) {
        try {
            LocalDate.fromDateFields(date);
        } catch (Exception e) {
            LOGGER.info("error", e);
            return false;
        }
        return true;

    }

    public static DateTime currentDate() {
        DateTime now = DateTime.parse(currentDateAsStr());
        return now;
    }

    public static String dateAsYYYYMMDDString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }
    
    public static String dateTimeAsYYYYMMDDString(DateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();
        return datetime.toString(formatter);
    }

    public static DateTime dateTimeAsYYYYMMDD(String dateString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();
        return DateTime.parse(dateString, formatter);
    }

    public static Date defaultDate() {
        String dateString = "9999-12-31";
        Date defaultDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            defaultDate = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultDate;
    }

    public static DateTime defaultDateTime() {
        String dateString = "9999-12-31";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();
        return DateTime.parse(dateString, formatter);
    }

    public static Date dateAsYYYYMMDD(String dateString) {
        Date defaultDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            defaultDate = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultDate;
    }

    public static Date setDateAsYYYYMMDD(DateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();
        String dateTimeString = formatter.print(dateTime);
        return formatter.parseLocalDate(dateTimeString).toDate();
    }

    public static DateTime dateTimeAsYYYYMMDD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();
        String dateString = format.format(date);
        return DateTime.parse(dateString, formatter);
    }

    public static DateTime dateTimeAsYYYYMM(String dateString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM").withZoneUTC();
        DateTime dateTime = DateTime.parse(dateString, formatter);
        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), 1, 0, 0);
    }

    public static DateTime setDateTimeAsYYYYMM(DateTime dateTime) {
        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), 1, 0, 0);
    }

    public static DateTime getAdjustedDate(DateTime dateTime, boolean excludeWeekend, int daysToAdd) {
        if (daysToAdd == 0 && excludeWeekend) {
            if (dateTime.getDayOfWeek() == DateTimeConstants.SATURDAY) {
                return dateTime.minusDays(1);
            }
            if (dateTime.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                return dateTime.plusDays(1);
            }
        }
        if (daysToAdd != 0 && !excludeWeekend) {
            return dateTime.plusDays(daysToAdd);
        }
        if (daysToAdd != 0 && excludeWeekend) {
            DateTime newDateTime = dateTime.plusDays(daysToAdd);
            if (newDateTime.getDayOfWeek() == DateTimeConstants.SATURDAY) {
                return newDateTime.minusDays(1);
            }
            if (newDateTime.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                return newDateTime.plusDays(1);
            }
        }
        return dateTime;
    }

    public static Date dateAsYYYY(String dateString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy").withZoneUTC();
        return new Date(formatter.parseMillis(dateString));
    }

}