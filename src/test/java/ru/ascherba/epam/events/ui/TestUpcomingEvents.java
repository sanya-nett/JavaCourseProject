package ru.ascherba.epam.events.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.helpers.api.EventCardHelper;
import ru.ascherba.epam.events.steps.EventPageSteps;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Epic("EPAM Events")
@Feature("Страница предстоящих событий")
public class TestUpcomingEvents extends BaseTest {

    EventPageSteps eventSteps = new EventPageSteps();

    @Test
    @DisplayName("Просмотр предстоящих мероприятий")
    public void shouldViewUpcomingEvents() {
        eventSteps.userMoveToEventsTab();
        eventSteps.userClickOnUpcomingEvents();
        eventSteps.eventCardsPresentedOnPage();
        eventSteps.eventCardNumberEqualToUpcomingEventCounter();
    }

    @Test
    @DisplayName("Валидация дат предстоящих мероприятий")
    public void shouldValidatingDates() {
        eventSteps.userMoveToEventsTab();
        eventSteps.userClickOnUpcomingEvents();
        eventSteps.eventCardsPresentedOnPage();
        assumeTrue(new EventCardHelper().haveThisWeekEvents());
        eventSteps.weekEventDatesInCurrentWeekRange();
    }
}
