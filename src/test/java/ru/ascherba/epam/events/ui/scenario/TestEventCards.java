package ru.ascherba.epam.events.ui.scenario;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.entities.events.Event;
import ru.ascherba.epam.events.helpers.EventCardHelper;
import ru.ascherba.epam.events.steps.EventPageSteps;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Epic("EPAM Events")
@Feature("Карточки мероприятий")
public class TestEventCards extends BaseTest {

    EventPageSteps eventSteps = new EventPageSteps();
    EventCardHelper eventCardHelper = new EventCardHelper();

    @Test
    @DisplayName("Просмотр карточек мероприятий")
    public void shouldHaveInformationAboutEvent() {
        eventSteps.userMoveToEventsTab();
        eventSteps.userClickOnUpcomingEvents();
        eventSteps.eventCardsPresentedOnPage();
        List<Event> responseEvents = eventCardHelper
                .getUpcomingResponse()
                .events;
        eventSteps.eventCardsEqualToResponseCards(responseEvents);
    }

    @Test
    @DisplayName("Просмотр детальной информации о мероприятии")
    public void shouldHaveDetailedInformationAboutEvent() {
        eventSteps.userMoveToEventsTab();
        eventSteps.userClickOnUpcomingEvents();
        eventSteps.eventCardsPresentedOnPage();
        String eventTitle = eventSteps.userClickOnRandomCard();
        Event responseEvent = eventCardHelper.getUpcomingEvent(eventTitle);
        eventSteps.openEventCardDetailPage(responseEvent);
        step("На странице с информацией о мероприятии отображается блок с кнопкой для регистрации, " +
                "дата и время, программа мероприятия", () -> {
            $(".evnt-panel-wrapper")
                    .find(byText(responseEvent.title))
                    .shouldBe(Condition.visible);
        });
    }
}
