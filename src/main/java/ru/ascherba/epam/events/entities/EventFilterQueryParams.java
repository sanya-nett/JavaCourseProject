package ru.ascherba.epam.events.entities;

import ru.ascherba.epam.events.entities.talks.EventTalkFilterCategory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aleksandr.scherba on 07.02.2021
 * Builder for creating of event filter query parameters
 */
public class EventFilterQueryParams {

    private final List<EventTalkFilterCategory> eventFilters;
    private final HashMap<String, Object> queryParameters = new HashMap<>();

    public EventFilterQueryParams(List<EventTalkFilterCategory> eventFilters) {
        this.eventFilters = eventFilters;
    }

    /**
     * Add parameters from filter items
     *
     * @param parameterName filter name
     * @param filterValue   value by which the values are selected
     * @return self instance of builder
     */
    public EventFilterQueryParams addParams(String parameterName, String filterValue) {
        for (EventTalkFilterCategory eventFilter : eventFilters) {
            if (eventFilter.name.equals(parameterName)) {
                queryParameters.put(eventFilter.id + "[]", eventFilter.getFilterItems(filterValue));
            }
        }
        return this;
    }

    /**
     * Collect filter query parameters
     *
     * @return query parameters
     */
    public HashMap<String, Object> collect() {
        return queryParameters;
    }
}
