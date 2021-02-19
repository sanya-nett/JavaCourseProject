package ru.ascherba.epam.events.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ascherba.epam.events.entities.EventFilterQueryParams;
import ru.ascherba.epam.events.entities.talks.EventTalkResponseCard;
import ru.ascherba.epam.events.helpers.api.EventCardTalkFilterHelper;
import ru.ascherba.epam.events.steps.VideoPageSteps;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;

/**
 * Created by aleksandr.scherba on 23.01.2021
 */
@Epic("EPAM Events")
@Feature("Страница докладов")
public class TestVideoPresentations extends BaseTest {

    VideoPageSteps videoSteps = new VideoPageSteps();

    @Step("Получить список отфильтрованных карточек с помощью API")
    public List<EventTalkResponseCard> getEventTalks() {
        EventCardTalkFilterHelper eventHelper = new EventCardTalkFilterHelper();
        EventFilterQueryParams eventQueryBuilder = new EventFilterQueryParams(eventHelper.getAllFilters());
        Map<String, Object> queryParams = eventQueryBuilder
                .addParams("Media", "Video")
                .addParams("Category", "Testing")
                .addParams("Location", "Belarus")
                .addParams("Language", "ENGLISH")
                .collect();
        return eventHelper.getEventTalks(queryParams);
    }

    @Test
    @DisplayName("Фильтрация докладов по категориям")
    public void shouldViewVideoFilteredByCategory() {
        videoSteps.userMoveToVideoTab();
        videoSteps.userClickOnFilterShowMore();
        step("Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, " +
                "На развернутой вкладке фильтров", () -> {
            videoSteps.userSelectCategoryFromFilter("Testing");
            videoSteps.userSelectLocationFromFilter("Belarus");
            videoSteps.userSelectLanguageFromFilter("ENGLISH");
        });

        List<EventTalkResponseCard> eventTalkCards = getEventTalks();
        step("На странице отображаются карточки соответствующие правилам выбранных фильтров", () -> {
            videoSteps.eventTalkCardsCountEqualResponseCards(eventTalkCards);
            videoSteps.eventTalkCardsNamedEqualResponseCards(eventTalkCards);
            videoSteps.eventTalkCardsLanguageEqualResponseCards(eventTalkCards);
            videoSteps.eventTalkCardsHaveVideoMedia();
        });
    }

    @Test
    @DisplayName("Поиск докладов по ключевому слову")
    public void shouldSearchVideoByKeyword() {
        videoSteps.userMoveToVideoTab();
        videoSteps.userTypeKeywordToFilterSearch("QA");
        videoSteps.eventTalkCardsContainSubstring("QA");
    }
}
