package ru.ascherba.epam.events.helpers.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.mapper.ObjectMapperType;
import ru.ascherba.epam.events.entities.talks.EventTalkFilterCategory;
import ru.ascherba.epam.events.entities.talks.EventTalkFilterItem;
import ru.ascherba.epam.events.entities.talks.EventTalkResponse;
import ru.ascherba.epam.events.entities.talks.EventTalkResponseCard;
import ru.ascherba.epam.events.helpers.EventFilterItemsJsonDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by aleksandr.scherba on 01.02.2021
 */
public class EventCardTalkFilterHelper extends EventBaseHelper {

    public EventCardTalkFilterHelper() {
        super();
    }

    /**
     * Get all available filter event talks items from backend
     *
     * @return list of filters
     */
    public List<EventTalkFilterCategory> getAllFilters() {
        String response = given()
                .get("/api/v2/filters/talks.json")
                .asString();
        Gson mapper = new GsonBuilder()
                .registerTypeAdapter(EventTalkFilterItem.class, new EventFilterItemsJsonDeserializer())
                .create();
        return Arrays.asList(mapper.fromJson(response, EventTalkFilterCategory[].class));
    }

    /**
     * Get filtered event talks from backend
     *
     * @param queryParameters parameters for select cards
     * @return event talk card objects
     */
    public List<EventTalkResponseCard> getEventTalks(Map<String, Object> queryParameters) {
        return given()
                .queryParams(queryParameters)
                .get("/api/v2/talks.json")
                .then()
                .extract()
                .body()
                .as(EventTalkResponse.class, ObjectMapperType.GSON)
                .talks;
    }
}
