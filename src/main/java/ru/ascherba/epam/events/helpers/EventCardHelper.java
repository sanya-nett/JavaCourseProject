package ru.ascherba.epam.events.helpers;

import io.restassured.mapper.ObjectMapperType;
import ru.ascherba.epam.events.entities.events.EventResponse;

import static io.restassured.RestAssured.given;

/**
 * Created by aleksandr.scherba on 14.02.2021
 */
public class EventCardHelper extends EventBaseHelper {

    public EventCardHelper() {
        super();
    }

    /**
     * Get response for special period type
     *
     * @param period time period type ("past", "upcoming")
     * @return response object
     */
    private EventResponse getEventResponse(String period) {
        return given()
                .queryParam("start", 0)
                .queryParam("period", period)
                .get("/api/v2/events.json")
                .then()
                .extract()
                .body()
                .as(EventResponse.class, ObjectMapperType.GSON);
    }

    /**
     * Ger response for upcoming period
     *
     * @return response object
     */
    public EventResponse getUpcomingResponse() {
        return getEventResponse("upcoming");
    }

    /**
     * Сheck that there are events for this week
     *
     * @return boolean result
     */
    public boolean haveThisWeekEvents() {
        return !getUpcomingResponse()
                .nearestEvents
                .thisWeek
                .isEmpty();
    }
}
