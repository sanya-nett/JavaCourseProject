package ru.ascherba.epam.events.containers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class EventTalkCard {

    private final SelenideElement container;
    private final SelenideElement tooltipElement = $("div.tooltip-inner");

    public EventTalkCard(SelenideElement container) {
        this.container = container;
    }

    private SelenideElement getNameElement() {
        return container.$("div.evnt-talk-name span");
    }

    private SelenideElement getLanguageElement() {
        return container.$("p.language");
    }

    private SelenideElement getVideoElement() {
        return container.$("p.video");
    }

    @Step("Scroll to event name and get name from tooltip")
    private String getEventFullNameFromTooltip() {
        getNameElement().scrollTo();
        actions().moveToElement(getNameElement()).perform();
        return tooltipElement.getText();
    }

    @Step("Get full talk name")
    public String getEventFullName() {
        String eventName = getNameElement().getText();
        if (eventName.endsWith("…")) {
            eventName = getEventFullNameFromTooltip();
        }
        return eventName;
    }

    @Step("Get language")
    public String getEventLanguage() {
        return getLanguageElement().getText();
    }

    @Step("Get video")
    public String getEventVideo() {
        return getVideoElement().getText();
    }

}
