package ru.ascherba.epam.events.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.steps.EventPageSteps;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Epic("EPAM Events")
@Feature("Страница прошедших событий")
public class TestPastEvents extends BaseTest {

    EventPageSteps eventSteps = new EventPageSteps();

    @Test
    @DisplayName("Просмотр прошедших мероприятий в Канаде")
    public void shouldViewPastEventsFilteredByLocation() {
        eventSteps.userMoveToEventsTab();
        eventSteps.userClickOnPastEvents();
        eventSteps.userSelectCountryFromLocationFilter("Canada");
        eventSteps.eventCardsPresentedOnPage();
        eventSteps.eventCardNumberEqualToPastEventCounter();
        eventSteps.allEventDatesLessThenDate();
    }
}
