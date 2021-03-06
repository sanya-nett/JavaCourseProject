package ru.ascherba.epam.events.steps;

import io.qameta.allure.Step;
import ru.ascherba.epam.events.containers.EventCard;
import ru.ascherba.epam.events.entities.EventDatePeriod;
import ru.ascherba.epam.events.entities.events.Event;
import ru.ascherba.epam.events.pages.EventPage;
import ru.ascherba.epam.events.pages.MainPage;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class EventPageSteps extends BasePageSteps {

    MainPage mainPage;
    EventPage eventPage;

    @Step("Пользователь переходит на вкладку events")
    public void userMoveToEventsTab() {
        mainPage = openMainPage();
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

    @Step("Пользователь нажимает на любую карточку")
    public String userClickOnRandomCard() {
        Random random = new Random();
        List<EventCard> eventCardList = eventPage.getAllEventCards();
        EventCard eventCard = eventCardList
                .get(random.nextInt(eventCardList.size()));
        String eventName = eventCard.getEventName();
        eventCard.clickOnCard();
        return eventName;
    }

    @Step("На странице отображаются карточки предстоящих мероприятий")
    public void eventCardsPresentedOnPage() {
        assertThat(eventPage.getAllEventCards())
                .withFailMessage("Event cards are not displayed on the page")
                .isNotEmpty();
    }

    @Step("Происходит переход на страницу с подробной информацией о мероприятии")
    public void openEventCardDetailPage(Event responseEvent) {
        assertThat(url())
                .withFailMessage("URL path doesn't not end with value from api response")
                .endsWith("/" + responseEvent.url);
    }

    @Step("Количество карточек равно счетчику на кнопке Upcoming Events")
    public void eventCardNumberEqualToUpcomingEventCounter() {
        assertThat(eventPage.getAllEventCards())
                .withFailMessage("The number of cards does not match the value in the counter")
                .hasSize(eventPage.getUpcomingEventsCounterValue());
    }

    @Step("Количество карточек равно счетчику на кнопке Past Events")
    public void eventCardNumberEqualToPastEventCounter() {
        assertThat(eventPage.getAllEventCards())
                .withFailMessage("The number of cards does not match the value in the counter")
                .hasSize(eventPage.getPastEventsCounterValue());
    }

    @Step("В блоке 'This week' даты проведения мероприятий больше или равны текущей дате " +
            "и находятся в пределах текущей недели")
    public void weekEventDatesInCurrentWeekRange() {
        for (EventCard event : eventPage.getWeekEventCards()) {
            assertThat(event.getEventPeriod().isAvailableForThisWeek())
                    .withFailMessage(event.getEventName() + " doesn't available on this week")
                    .isTrue();
        }
    }

    @Step("Даты проведенных мероприятий меньше текущей даты")
    public void allEventDatesLessThenDate() {
        for (EventCard event : eventPage.getAllEventCards()) {
            assertThat(event.getEventPeriod().isPassed())
                    .withFailMessage(event.getEventName() + " doesn't passed")
                    .isTrue();
        }
    }

    @Step("Найти информацию о '{eventTitle}' мероприятии из API")
    private Event searchEventFromResponse(String eventTitle, List<Event> responseEvents) {
        Optional<Event> eventResult = responseEvents
                .stream()
                .filter(event -> event.title.equals(eventTitle))
                .findFirst();
        assertThat(eventResult.isPresent())
                .withFailMessage(eventTitle + " event not found")
                .isTrue();
        return eventResult.get();
    }

    @Step("В карточке указана информация о месте проведения")
    private void eventCardLocationEqualToResponseCard(EventCard card, Event event) {
        assertThat(card.getEventLanguage())
                .withFailMessage("Event card's language isn't equal from api response")
                .isEqualTo(event.language);
    }

    @Step("В карточке указана информация о дате проведения")
    private void eventCardDateEqualToResponseCard(EventCard card, Event event) {
        EventDatePeriod expectedPeriod = new EventDatePeriod(event.dates);
        EventDatePeriod actualPeriod = card.getEventPeriod();
        assertThat(actualPeriod.getStartDate())
                .withFailMessage("Event card's start date isn't equal from api response")
                .isEqualTo(expectedPeriod.getStartDate());
        assertThat(actualPeriod.getEndDate())
                .withFailMessage("Event card's end date isn't equal from api response")
                .isEqualTo(expectedPeriod.getEndDate());
    }

    @Step("В карточке указана информация о регистрации")
    private void eventCardRegStatusEqualToResponseCard(EventCard card, Event event) {
        if (event.eventStatus != null) {
            assertThat(card.getEventRegisterStatus())
                    .withFailMessage("Event card's register status isn't equal from api response")
                    .isEqualTo(event.eventStatus.text);
        } else {
            card.checkThatEventRegisterStatusIsNotVisible();
        }
    }

    @Step("В карточке указана информация о спикерах")
    private void eventCardSpeakersEqualToResponseCard(EventCard card, Event event) {
        if (event.speakers.count.equals(0)) {
            card.checkThatEventSpeakersUnknown();
        } else {
            assertThat(card.getEventSpeakerCount())
                    .withFailMessage("Event card's speaker count isn't equal from api response")
                    .isEqualTo(event.speakers.count);
        }
    }

    @Step("В карточках указана информация о мероприятии")
    public void eventCardsEqualToResponseCards(List<Event> responseEvents) {
        for (EventCard eventCard : eventPage.getAllEventCards()) {
            Event eventResponse = searchEventFromResponse(eventCard.getEventName(), responseEvents);
            eventCardLocationEqualToResponseCard(eventCard, eventResponse);
            eventCardDateEqualToResponseCard(eventCard, eventResponse);
            eventCardRegStatusEqualToResponseCard(eventCard, eventResponse);
            eventCardSpeakersEqualToResponseCard(eventCard, eventResponse);
        }
    }
}
