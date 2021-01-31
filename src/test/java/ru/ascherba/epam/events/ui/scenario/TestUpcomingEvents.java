package ru.ascherba.epam.events.ui.scenario;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.steps.EventPageSteps;

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
        eventSteps.weekEventDatesInCurrentWeekRange();
    }
}
