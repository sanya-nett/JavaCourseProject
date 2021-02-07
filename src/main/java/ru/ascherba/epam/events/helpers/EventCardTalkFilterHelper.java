package ru.ascherba.epam.events.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import ru.ascherba.epam.events.entities.talks.EventTalkFilterCategory;
import ru.ascherba.epam.events.entities.talks.EventTalkFilterItem;
import ru.ascherba.epam.events.entities.talks.EventTalkResponse;
import ru.ascherba.epam.events.entities.talks.EventTalkResponseCard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by aleksandr.scherba on 01.02.2021
 */
public class EventCardTalkFilterHelper {

    public EventCardTalkFilterHelper() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        // FIXME: need use a common configuration class
        RestAssured.baseURI = "https://events.epam.com";
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
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
    public List<EventTalkResponseCard> getEventTalks(HashMap<String, Object> queryParameters) {
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
