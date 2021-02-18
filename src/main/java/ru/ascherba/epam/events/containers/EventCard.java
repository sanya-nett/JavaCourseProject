package ru.ascherba.epam.events.containers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ascherba.epam.events.entities.EventDatePeriod;

import static com.codeborne.selenide.Condition.attribute;

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

    @Step("Click on event card")
    public void clickOnCard() {
        container.click();
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

    @Step("Check that register status is not visible")
    public void checkThatEventRegisterStatusIsNotVisible() {
        getRegisterStatusElement().shouldNot(Condition.exist);
    }

    @Step("Get event date")
    public EventDatePeriod getEventPeriod() {
        return new EventDatePeriod(getDateElement().getText());
    }

    @Step("Get event speaker count")
    public int getEventSpeakerCount() {
        return getSpeakerElements().size();
    }

    @Step("Check that event speakers unknown")
    public void checkThatEventSpeakersUnknown() {
        getSpeakerElements().shouldHaveSize(1);
        getSpeakerElements().get(0)
                .shouldHave(attribute(
                        "data-name",
                        "Speaker"))
                .shouldHave(attribute(
                        "data-job-title",
                        "Information about the speaker will be available soon"));
    }

}
