package ru.ascherba.epam.events.unit;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.entities.EventDatePeriod;
import ru.ascherba.epam.events.helpers.DateUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by aleksandr.scherba on 24.01.2021
 */
@Epic("Unit tests")
@Feature("Entities")
@Story("EventsDatePeriod")
class EventDatePeriodSimpleTest {

    EventDatePeriod eventDatePeriod;
    LocalDate weekStartDay = DateUtils.getWeekStartDay();
    LocalDate weekEndDay = DateUtils.getWeekEndDay();

    @Test
    public void shouldTrueIfDateOnThisWeek() {
        eventDatePeriod = new EventDatePeriod(weekStartDay.plusDays(1));
        assertTrue(eventDatePeriod.isAvailableForThisWeek());
    }

    @Test
    public void shouldTrueIfDateEqualToStartWeek() {
        eventDatePeriod = new EventDatePeriod(weekStartDay);
        assertTrue(eventDatePeriod.isAvailableForThisWeek());
    }

    @Test
    public void shouldTrueIfDateEqualToEndWeek() {
        eventDatePeriod = new EventDatePeriod(weekEndDay);
        assertTrue(eventDatePeriod.isAvailableForThisWeek());
    }

    @Test
    public void shouldFalseIfDateLessThenStartWeek() {
        eventDatePeriod = new EventDatePeriod(weekStartDay.minusDays(1));
        assertFalse(eventDatePeriod.isAvailableForThisWeek());
    }

    @Test
    public void shouldFalseIfDateMoreThenEndWeek() {
        eventDatePeriod = new EventDatePeriod(weekEndDay.plusDays(1));
        assertFalse(eventDatePeriod.isAvailableForThisWeek());
    }

}