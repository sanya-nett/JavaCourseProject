package ru.ascherba.epam.events.ui.scenario;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Feature("Страница прошедших событий")
public class TestPastEvents {

    @Test
    @DisplayName("Просмотр прошедших мероприятий в Канаде")
    public void shouldViewPastEventsFilteredByLocation() {
        // Даты проведенных мероприятий меньше текущей даты
        step("Пользователь переходит на вкладку events", () -> {});
        step("Пользователь нажимает на Past Events", () -> {});
        step("Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке", () -> {});
        step("На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику " +
                "на кнопке Past Events.", () -> {});
    }
}
