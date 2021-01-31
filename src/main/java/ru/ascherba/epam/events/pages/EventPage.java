package ru.ascherba.epam.events.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.NotFoundException;
import ru.ascherba.epam.events.containers.EventCard;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class EventPage {

    SelenideElement upcomingEventsTab = $(byText("Upcoming events")).closest(".evnt-tab-item");
    SelenideElement pastEventsTab = $(byText("Past Events")).closest(".evnt-tab-item");
    ElementsCollection allEventCards = $$(".evnt-cards-container .evnt-card-wrapper");
    ElementsCollection weekEventCards = $$x("//h3[text()='This Week']/..//div[contains(@class, 'evnt-event-card')]");

    public SelenideElement getUpcomingEventsTab() {
        return upcomingEventsTab;
    }

    public SelenideElement getPastEventsTab() {
        return pastEventsTab;
    }

    private int getEventTabCounterValue(SelenideElement eventTabElement) {
        return Integer.parseInt(eventTabElement
                .$(".evnt-tab-counter")
                .getText());
    }

    private List<EventCard> castElementsToEventCards(ElementsCollection eventCardElements) {
        List<EventCard> eventCardList = new ArrayList<>();
        for (SelenideElement element : eventCardElements) {
            eventCardList.add(new EventCard(element));
        }
        return eventCardList;

    }

    @Step("Get counter from 'Upcoming events' navigation button")
    public int getUpcomingEventsCounterValue() {
        return getEventTabCounterValue(upcomingEventsTab);
    }

    @Step("Get counter from 'Upcoming events' navigation button")
    public int getPastEventsCounterValue() {
        return getEventTabCounterValue(pastEventsTab);
    }

    @Step("Get all event cards")
    public List<EventCard> getAllEventCards() {
        return castElementsToEventCards(allEventCards);
    }

    @Step("Get 'This week' event cards")
    public List<EventCard> getWeekEventCards() {
        if (weekEventCards.isEmpty()) {
            throw new NotFoundException("Not found week event cards");
        }
        return castElementsToEventCards(weekEventCards);
    }
}
