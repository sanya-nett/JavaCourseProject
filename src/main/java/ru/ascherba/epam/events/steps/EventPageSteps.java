package ru.ascherba.epam.events.steps;

import io.qameta.allure.Step;
import ru.ascherba.epam.events.containers.EventCard;
import ru.ascherba.epam.events.pages.EventPage;
import ru.ascherba.epam.events.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class EventPageSteps {

    MainPage mainPage;
    EventPage eventPage;

    @Step("Пользователь переходит на вкладку events")
    public void userMoveToEventsTab() {
        mainPage = open("/", MainPage.class);
        mainPage.platformHeader.clickOnEventTab();
        eventPage = new EventPage();
    }

    @Step("Пользователь нажимает на Upcoming Events")
    public void userClickOnUpcomingEvents() {
        eventPage.getUpcomingEventsTab().click();
    }

    @Step("Пользователь нажимает на Past Events")
    public void userClickOnPastEvents() {
        eventPage.getPastEventsTab().click();
    }

    @Step("Пользователь нажимает на Location в блоке фильтров и выбирает {country} в выпадающем списке")
    public void userSelectCountryFromLocationFilter(String country) {
        eventPage.eventFilterPanel.clickOnLocationFilter();
        eventPage.eventFilterPanel.selectCountryFromLocationFilter(country);
        eventPage.eventFilterPanel.clickOnLocationFilter();
    }

    @Step("На странице отображаются карточки предстоящих мероприятий")
    public void eventCardsPresentedOnPage() {
        assertFalse(eventPage.getAllEventCards().isEmpty());
    }

    @Step("Количество карточек равно счетчику на кнопке Upcoming Events")
    public void eventCardNumberEqualToUpcomingEventCounter() {
        assertEquals(eventPage.getUpcomingEventsCounterValue(), eventPage.getAllEventCards().size());
    }

    @Step("Количество карточек равно счетчику на кнопке Past Events")
    public void eventCardNumberEqualToPastEventCounter() {
        assertEquals(eventPage.getPastEventsCounterValue(), eventPage.getAllEventCards().size());
    }

    @Step("В блоке 'This week' даты проведения мероприятий больше или равны текущей дате " +
            "и находятся в пределах текущей недели")
    public void weekEventDatesInCurrentWeekRange() {
        for (EventCard event : eventPage.getWeekEventCards()) {
            assertTrue(event.getEventPeriod().isAvailableForThisWeek());
        }
    }

    @Step("Даты проведенных мероприятий меньше текущей даты")
    public void allEventDatesLessThenDate() {
        for (EventCard event : eventPage.getAllEventCards()) {
            assertTrue(event.getEventPeriod().isPassed());
        }
    }
}
