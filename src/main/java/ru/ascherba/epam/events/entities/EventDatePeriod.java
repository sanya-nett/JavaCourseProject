package ru.ascherba.epam.events.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.ascherba.epam.events.helpers.DateUtils.*;

/**
 * Created by aleksandr.scherba on 24.01.2021
 */
public class EventDatePeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public EventDatePeriod(LocalDate eventDate) {
        this.startDate = eventDate;
        this.endDate = eventDate;
    }

    public EventDatePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventDatePeriod(String eventDate) {
        List<String> dateStringList = Arrays.asList(eventDate.trim().split(" - "));
        if (dateStringList.size() == 2) {
            endDate = parseFullEventDate(dateStringList.get(1));
            startDate = parseShortEventDate(dateStringList.get(0), endDate);
        } else if (dateStringList.size() == 1){
            startDate = parseFullEventDate(dateStringList.get(0));
            endDate = startDate;
        } else {
            throw new AssertionError("Error getting event date from string");
        }
    }

    /**
     * Parse full event date
     * @param dateString date string
     * @return date object
     */
    private LocalDate parseFullEventDate(String dateString) {
        return parseFullDate("d MMM yyyy", dateString);
    }

    /**
     * Parse short event date
     *
     * @param dateString  date string
     * @param defaultDate default date
     * @return date object
     */
    private LocalDate parseShortEventDate(String dateString, LocalDate defaultDate) {
        return parseShortDate("d MMM", dateString, defaultDate);
    }

    /**
     * Check that current event date period on this week
     *
     * @return boolean value
     */
    public boolean isAvailableForThisWeek() {
        return dateOnThisWeek(startDate) || dateOnThisWeek(endDate);
    }

    /**
     * Check that current event date period in the past
     *
     * @return boolean value
     */
    public boolean isPassed() {
        return dateInPast(endDate);
    }
}
