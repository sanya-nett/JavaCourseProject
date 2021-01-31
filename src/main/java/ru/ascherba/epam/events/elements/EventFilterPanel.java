package ru.ascherba.epam.events.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class EventFilterPanel {

    SelenideElement panel = $("section.evnt-filters-panel");
    SelenideElement locationFilter = panel.$("#filter_location");
    SelenideElement locationFilterList = panel.$("[aria-labelledby='filter_location']");

    @Step("Click on 'Location' filter")
    public void clickOnLocationFilter() {
        locationFilter.click();
    }

    @Step("Select {country} from location filter")
    public void selectCountryFromLocationFilter(String country) {
        locationFilterList.$(withText(country)).click();
    }

}
