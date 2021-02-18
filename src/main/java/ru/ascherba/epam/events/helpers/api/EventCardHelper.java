package ru.ascherba.epam.events.helpers.api;

import io.restassured.mapper.ObjectMapperType;
import ru.ascherba.epam.events.entities.events.Event;
import ru.ascherba.epam.events.entities.events.EventResponse;

import java.util.NoSuchElementException;
import java.util.Optional;

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
     * Get event by name from upcoming events
     *
     * @param eventTitle expected event name for search
     * @return event instance
     */
    public Event getUpcomingEvent(String eventTitle) {
        Optional<Event> event = getUpcomingResponse()
                .events
                .stream()
                .filter(e -> e.title.equals(eventTitle))
                .findFirst();
        if (!event.isPresent()) {
            throw new NoSuchElementException(
                    String.format("Not found event with name: %s", eventTitle));
        }
        return event.get();
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
