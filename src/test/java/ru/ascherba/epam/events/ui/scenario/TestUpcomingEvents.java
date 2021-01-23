package ru.ascherba.epam.events.ui.scenario;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Feature("Страница предстоящих событий")
public class TestUpcomingEvents {

    @Test
    @DisplayName("Просмотр предстоящих мероприятий")
    public void shouldViewUpcomingEvents() {
        step("Пользователь переходит на вкладку events", () -> {});
        step("Пользователь нажимает на Upcoming Events", () -> {});
        step("На странице отображаются карточки предстоящих мероприятий", () -> {});
        step("Количество карточек равно счетчику на кнопке Upcoming Events", () -> {});
    }

    @Test
    @DisplayName("Валидация дат предстоящих мероприятий")
    public void shouldValidatingDates() {
        step("Пользователь переходит на вкладку events", () -> {});
        step("Пользователь нажимает на Upcoming Events", () -> {});
        step("На странице отображаются карточки предстоящих мероприятий", () -> {});
        step("В блоке 'This week' даты проведения мероприятий больше или равны текущей дате " +
                "и находятся в пределах текущей недели", () -> {});
    }
}
