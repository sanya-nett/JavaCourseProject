package ru.ascherba.epam.events.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class DateUtils {

    /**
     * Get start date for this week
     * @return monday date
     */
    public static LocalDate getWeekStartDay() {
        LocalDate monday = LocalDate.now();
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }
        return monday;
    }

    /**
     * Get end date for this week
     * @return sunday date
     */
    public static LocalDate getWeekEndDay() {
        LocalDate sunday = LocalDate.now();
        while (sunday.getDayOfWeek() != DayOfWeek.MONDAY) {
            sunday = sunday.plusDays(1);
        }
        return sunday;
    }

    /**
     * Create instance of DateTimeFormatter from string template
     * @param dateTimeFormat string template using for parse
     * @return formatter
     */
    private static DateTimeFormatter createDateFormatter(String dateTimeFormat) {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern(dateTimeFormat))
                .toFormatter(Locale.ENGLISH);
    }

    /**
     * Checks that the date is on the current week
     * Сheck includes week boundaries [Start day of Week: End day of Week]
     * @param date verifiable date
     * @return true if date inside current week
     */
    public static boolean dateOnThisWeek(LocalDate date) {
        return date.compareTo(getWeekStartDay()) >= 0 && date.compareTo(getWeekEndDay()) <= 0;
    }

    /**
     * Parse full date by passed datetime format
     * Date can be any (For example: '2 Dec 2021')
     * Date format can be any (For example: 'dd MMM yyyy')
     * @param dateTimeFormat string template using for parse
     * @param dateString string that need to parse
     * @return date object
     */
    public static LocalDate parseFullDate(String dateTimeFormat, String dateString) {
        return LocalDate.parse(dateString, createDateFormatter(dateTimeFormat));
    }

    /**
     * Parse short date by passed datetime format
     * Date can be any (For example: '2 Dec')
     * Date format can be any (For example: 'dd MMM')
     * @param dateTimeFormat string template using for parse
     * @param dateString string that need to parse
     * @param year default year (need to create date object)
     * @return date object
     */
    public static LocalDate parseShortDate(String dateTimeFormat, String dateString, int year) {
        DateTimeFormatter formatter = createDateFormatter(dateTimeFormat);
        TemporalAccessor parsed = formatter.parse(dateString);
        MonthDay md = MonthDay.from(parsed);
        return LocalDate.of(year, md.getMonth(), md.getDayOfMonth());
    }
}
