package ru.ascherba.epam.events.unit;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.entities.EventDatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by aleksandr.scherba on 24.01.2021
 */
@Epic("Unit tests")
@Feature("Entities")
@Story("EventsDatePeriod")
class EventDatePeriodParserTest {

    EventDatePeriod eventDatePeriod;
    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    public void shouldParseSimpleDateString() {
        eventDatePeriod = new EventDatePeriod("19 Jan 2021");
        LocalDate expectedDate = LocalDate.of(2021, 1, 19);
        softAssertions.assertThat(eventDatePeriod.getStartDate())
                .isEqualTo(expectedDate);
        softAssertions.assertThat(eventDatePeriod.getEndDate())
                .isEqualTo(expectedDate);
        softAssertions.assertAll();
    }

    @Test
    public void shouldParseComplexDateString() {
        eventDatePeriod = new EventDatePeriod("19 Jan - 1 Feb 2021");
        LocalDate expectedStartDate = LocalDate.of(2021, 1, 19);
        LocalDate expectedEndDate = LocalDate.of(2021, 2, 1);
        softAssertions.assertThat(eventDatePeriod.getStartDate())
                .isEqualTo(expectedStartDate);
        softAssertions.assertThat(eventDatePeriod.getEndDate())
                .isEqualTo(expectedEndDate);
        softAssertions.assertAll();
    }

    @Test
    public void shouldThrowErrorWhenInvalidFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            new EventDatePeriod("19/01/21 - 01/02/21");
        });
    }

    @Test
    public void shouldThrowErrorWhenEmptyString() {
        assertThrows(DateTimeParseException.class, () -> {
            new EventDatePeriod("");
        });
    }

}