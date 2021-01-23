package ru.ascherba.epam.events.ui.scenario;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Feature("Страница докладов")
public class TestVideoPresentations {

    @Test
    @DisplayName("Фильтрация докладов по категориям")
    public void shouldViewVideoFilteredByCategory() {
        step("Пользователь переходит на вкладку Video", () -> {});
        step("Пользователь нажимает на More Filters", () -> {});
        step("Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, " +
                "На развернутой вкладке фильтров", () -> {});
        step("На странице отображаются карточки соответствующие правилам выбранных фильтров", () -> {});
    }

    @Test
    @DisplayName("Поиск докладов по ключевому слову")
    public void shouldSearchVideoByKeyword() {
        step("Пользователь переходит на вкладку Video", () -> {});
        step("Пользователь вводит ключевое слово QA в поле поиска", () -> {});
        step("На странице отображаются доклады, содержащие в названии ключевое слово поиска", () -> {});
    }
}
