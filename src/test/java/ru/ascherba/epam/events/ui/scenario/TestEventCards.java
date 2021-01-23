package ru.ascherba.epam.events.ui.scenario;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Feature("Карточки мероприятий")
public class TestEventCards {

    @Test
    @DisplayName("Просмотр карточек мероприятий")
    public void shouldHaveInformationAboutEvent() {
        step("Пользователь переходит на вкладку events", () -> {});
        step("Пользователь нажимает на Upcoming Events", () -> {});
        step("На странице отображаются карточки предстоящих мероприятий", () -> {});
        step("В карточке указана информация о мероприятии", () -> {
            // Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия
            step("место проведения, язык", () -> {});
            step("название мероприятия", () -> {});
            step("дата мероприятия", () -> {});
            step("информация о регистрации", () -> {});
            step("список спикеров", () -> {});
        });
    }

    @Test
    @DisplayName("Просмотр детальной информации о мероприятии")
    public void shouldHaveDetailedInformationAboutEvent() {
        step("Пользователь переходит на вкладку events", () -> {});
        step("Пользователь нажимает на Upcoming Events", () -> {});
        step("На странице отображаются карточки предстоящих мероприятий", () -> {});
        step("Пользователь нажимает на любую карточку", () -> {});
        step("Происходит переход на страницу с подробной информацией о мероприятии", () -> {});
        step("На странице с информацией о мероприятии отображается блок с кнопкой для регистрации, " +
                "дата и время, программа мероприятия", () -> {});
    }
}
