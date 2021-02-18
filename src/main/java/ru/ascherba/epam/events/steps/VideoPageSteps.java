package ru.ascherba.epam.events.steps;

import io.qameta.allure.Step;
import ru.ascherba.epam.events.containers.EventTalkCard;
import ru.ascherba.epam.events.entities.talks.EventTalkResponseCard;
import ru.ascherba.epam.events.pages.MainPage;
import ru.ascherba.epam.events.pages.VideoPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class VideoPageSteps extends BasePageSteps {

    MainPage mainPage;
    VideoPage videoPage;

    @Step("Пользователь переходит на вкладку Video")
    public void userMoveToVideoTab() {
        mainPage = openMainPage();
        mainPage.platformHeader.clickOnVideoTab();
        videoPage = new VideoPage();
    }

    @Step("Пользователь нажимает на Category в блоке фильтров и выбирает {category} в выпадающем списке")
    public void userSelectCategoryFromFilter(String category) {
        videoPage.eventFilterPanel.clickOnCategoryFilter();
        videoPage.eventFilterPanel.selectFromCategoryFilter(category);
        videoPage.eventFilterPanel.clickOnCategoryFilter();
    }

    @Step("Пользователь нажимает на Location в блоке фильтров и выбирает {country} в выпадающем списке")
    public void userSelectLocationFromFilter(String country) {
        videoPage.eventFilterPanel.clickOnLocationFilter();
        videoPage.eventFilterPanel.selectCountryFromLocationFilter(country);
        videoPage.eventFilterPanel.clickOnLocationFilter();
    }

    @Step("Пользователь нажимает на Language в блоке фильтров и выбирает {language} в выпадающем списке")
    public void userSelectLanguageFromFilter(String language) {
        videoPage.eventFilterPanel.clickOnLanguageFilter();
        videoPage.eventFilterPanel.selectFromLanguageFilter(language);
        videoPage.eventFilterPanel.clickOnLanguageFilter();
    }

    @Step("Пользователь нажимает на More Filters")
    public void userClickOnFilterShowMore() {
        videoPage.eventFilterPanel.clickOnShowMore();
    }

    @Step("Пользователь вводит ключевое слово {keyword} в поле поиска")
    public void userTypeKeywordToFilterSearch(String keyword) {
        videoPage.eventFilterPanel.setSearchInput(keyword);
    }

    @Step("На странице отображаются доклады, содержащие в названии ключевое слово поиска '{keyword}'")
    public void eventTalkCardsContainSubstring(String keyword) {
        for (EventTalkCard eventTalk : videoPage.getEventTalkCards()) {
            assertThat(eventTalk.getEventFullName()).contains(keyword);
        }
    }

    @Step("Количество карточек с ответом из API")
    public void eventTalkCardsCountEqualResponseCards(List<EventTalkResponseCard> eventResponseCards) {
        assertThat(videoPage.getEventTalkCards().size())
                .isEqualTo(eventResponseCards.size());
    }

    @Step("Название докладов совпадает с ответом из API")
    public void eventTalkCardsNamedEqualResponseCards(List<EventTalkResponseCard> eventResponseCards) {
        List<String> expectedNames = eventResponseCards
                .stream()
                .map(eventTalkResponseCard -> eventTalkResponseCard.title)
                .collect(Collectors.toList());
        List<String> actualNames = videoPage.getEventTalkCards()
                .stream()
                .map(EventTalkCard::getEventFullName)
                .collect(Collectors.toList());
        assertThat(actualNames).isEqualTo(expectedNames);
    }

    @Step("Язык докладов совпадает с ответом из API")
    public void eventTalkCardsLanguageEqualResponseCards(List<EventTalkResponseCard> eventResponseCards) {
        List<String> expectedLanguages = eventResponseCards
                .stream()
                .map(eventTalkResponseCard -> eventTalkResponseCard.language)
                .collect(Collectors.toList());
        List<String> actualLanguages = videoPage.getEventTalkCards()
                .stream()
                .map(EventTalkCard::getEventLanguage)
                .collect(Collectors.toList());
        assertThat(actualLanguages).isEqualTo(expectedLanguages);
    }

    @Step("Все доклады имеют 'Video' тег")
    public void eventTalkCardsHaveVideoMedia() {
        List<String> actualVideos = videoPage.getEventTalkCards()
                .stream()
                .map(EventTalkCard::getEventVideo)
                .collect(Collectors.toList());
        assertThat(actualVideos).containsOnly("Video");
    }

}
