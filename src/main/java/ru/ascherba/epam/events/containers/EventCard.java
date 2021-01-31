package ru.ascherba.epam.events.containers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ascherba.epam.events.entities.EventDatePeriod;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class EventCard {

    private final SelenideElement container;

    public EventCard(SelenideElement container) {
        this.container = container;
    }

    private SelenideElement getLanguageElement() {
        return container.$("p.language > span");
    }

    private SelenideElement getNameElement() {
        return container.$("div.evnt-event-name span");
    }

    private SelenideElement getRegisterStatusElement() {
        return container.$("div.dates span.status");
    }

    private SelenideElement getDateElement() {
        return container.$("div.dates span.date");
    }

    private ElementsCollection getSpeakerElements() {
        return container.$$("div.speakers div.evnt-speaker");
    }

    @Step("Get event language")
    public String getEventLanguage() {
        return getLanguageElement().getText();
    }

    @Step("Get event name")
    public String getEventName() {
        return getNameElement().getText();
    }

    @Step("Get event register status")
    public String getEventRegisterStatus() {
        return getRegisterStatusElement().getText();
    }

    @Step("Get event date")
    public EventDatePeriod getEventPeriod() {
        return new EventDatePeriod(getDateElement().getText());
    }

    @Step("Get event speaker count")
    public int getEventSpeakerCount() {
        return getSpeakerElements().size();
    }

}
