package ru.ascherba.epam.events.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ascherba.epam.events.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by aleksandr.scherba on 18.02.2021
 */
public class BasePageSteps {

    SelenideElement acceptCookie = $("button#onetrust-accept-btn-handler");

    @Step("Принять настройки использования Cookies")
    void acceptCookieSettings() {
        if (acceptCookie.isDisplayed()) {
            acceptCookie.click();
        }
    }

    @Step("Открыть главную страницу")
    MainPage openMainPage() {
        MainPage mainPage = open("/", MainPage.class);
        acceptCookieSettings();
        return mainPage;
    }

}
