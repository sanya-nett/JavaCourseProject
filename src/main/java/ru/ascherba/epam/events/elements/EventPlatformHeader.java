package ru.ascherba.epam.events.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class EventPlatformHeader {

    SelenideElement header = $(".evnt-platform-header");
    SelenideElement eventTab = header.$("a[href='/events']");

    @Step("Click on 'Events' tab from header")
    public void clickOnEventTab() {
        eventTab.click();
    }

}
