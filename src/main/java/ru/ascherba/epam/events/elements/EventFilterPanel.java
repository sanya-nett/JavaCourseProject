package ru.ascherba.epam.events.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class EventFilterPanel {

    SelenideElement panel = $("section.evnt-filters-panel");
    SelenideElement searchInput = panel.$("input[type='text']");
    // Category filter
    SelenideElement categoryFilter = panel.$("#filter_category");
    SelenideElement categoryFilterList = panel.$("[aria-labelledby='filter_category']");
    // Location filter
    SelenideElement locationFilter = panel.$("#filter_location");
    SelenideElement locationFilterList = panel.$("[aria-labelledby='filter_location']");
    // Language Filter
    SelenideElement languageFilter = panel.$("#filter_language");
    SelenideElement languageFilterList = panel.$("[aria-labelledby='filter_language']");
    // Show more
    SelenideElement showMore = panel.$("div.show-more");

    private void waitResultsLoading() {
        // FIXME: bad way to check that results update
        $(".evnt-loader-wrapper").shouldBe(Condition.visible).shouldNotBe(Condition.visible);
    }

    private void selectFromFilter(SelenideElement filterElement, String value) {
        filterElement.$(byText(value)).click();
    }

    @Step("Type '{query}' in filter search field")
    public void setSearchInput(String query) {
        searchInput.setValue(query);
        waitResultsLoading();
    }

    @Step("Click on 'Category' filter")
    public void clickOnCategoryFilter() {
        categoryFilter.click();
    }

    @Step("Select {category} from location filter")
    public void selectFromCategoryFilter(String category) {
        selectFromFilter(categoryFilterList, category);
    }

    @Step("Click on 'Location' filter")
    public void clickOnLocationFilter() {
        locationFilter.click();
    }

    @Step("Select {country} from location filter")
    public void selectCountryFromLocationFilter(String country) {
        selectFromFilter(locationFilterList, country);
    }

    @Step("Click on 'Language' filter")
    public void clickOnLanguageFilter() {
        languageFilter.click();
    }

    @Step("Select {language} from language filter")
    public void selectFromLanguageFilter(String language) {
        selectFromFilter(languageFilterList, language);
    }

    @Step("Click on 'More Filters'")
    public void clickOnShowMore() {
        showMore.click();
    }

}
