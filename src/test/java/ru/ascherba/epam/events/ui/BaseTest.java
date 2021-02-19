package ru.ascherba.epam.events.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ascherba.epam.events.helpers.config.ConfigHelper;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
public class BaseTest {

    @BeforeAll
    static void configureSelenide() {
        Configuration.baseUrl = ConfigHelper.getHostname();
        Configuration.browser = ConfigHelper.getBrowser();
    }

    @BeforeEach
    void setUpSelenideLogger() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }
}
