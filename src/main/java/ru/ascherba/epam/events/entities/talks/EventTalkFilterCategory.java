package ru.ascherba.epam.events.entities.talks;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by aleksandr.scherba on 01.02.2021
 * Created automatically for POJO description
 */
public class EventTalkFilterCategory {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("items")
    public List<EventTalkFilterItem> items = null;

    /**
     * Get a list of filters that can be used for API request
     *
     * @param expectedFilterValue value by which the values are selected
     * @return filtered values
     * @throws NoSuchElementException if not found items by filter value
     */
    public List<String> getFilterItems(String expectedFilterValue) {
        List<String> filteredItems;
        for (EventTalkFilterItem eventTalkFilter : items) {
            filteredItems = eventTalkFilter.getFilterValues(expectedFilterValue, id.equals("location"));
            if (!filteredItems.isEmpty()) {
                return filteredItems;
            }
        }
        throw new NoSuchElementException(
                String.format("Not found items by %s filter in %s category",
                        expectedFilterValue, name));
    }

}
