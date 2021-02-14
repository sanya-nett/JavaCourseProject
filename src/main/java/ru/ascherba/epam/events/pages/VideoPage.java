package ru.ascherba.epam.events.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ascherba.epam.events.containers.EventTalkCard;
import ru.ascherba.epam.events.elements.EventFilterPanel;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by aleksandr.scherba on 31.01.2021
 */
public class VideoPage {

    ElementsCollection eventTalkCards = $$("div.evnt-talk-card");

    public EventFilterPanel eventFilterPanel = new EventFilterPanel();

    @Step("Get event talk cards")
    public List<EventTalkCard> getEventTalkCards() {
        List<EventTalkCard> eventCardList = new ArrayList<>();
        for (SelenideElement element : eventTalkCards) {
            eventCardList.add(new EventTalkCard(element));
        }
        return eventCardList;
    }
}
