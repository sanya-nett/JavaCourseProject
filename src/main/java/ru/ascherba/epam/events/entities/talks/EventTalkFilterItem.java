package ru.ascherba.epam.events.entities.talks;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by aleksandr.scherba on 01.02.2021
 * Created automatically for POJO description
 */
public class EventTalkFilterItem {
    @SerializedName("group")
    public String group;
    @SerializedName("items")
    public List<String> items = null;

    public EventTalkFilterItem(String group, List<String> items) {
        this.group = group;
        this.items = items;
    }

    /**
     * Convert a list of strings by prefixing each element
     * Note: must be used for strange location api implementation by location
     *
     * @param prefix   prefix that is added to the original element separated by commas
     * @param itemList list of strings to be converted
     * @return converted values
     */
    private static List<String> addPrefixListItems(String prefix, List<String> itemList) {
        List<String> convertedItems = new ArrayList<>();
        itemList.forEach(item -> convertedItems.add(String.format("%s, %s", prefix, item)));
        return convertedItems;
    }

    /**
     * Filtering group categories by value.
     * Used when searching in a sub-group items
     *
     * @param filterValue value by which the values are selected
     * @return filtered values
     */
    private List<String> getFilteredGroupValues(String filterValue) {
        return items.stream()
                .filter(item -> item.equals(filterValue))
                .collect(Collectors.toList());
    }

    /**
     * Filtering category group and its items
     *
     * @param filterValue    value by which the values are selected
     * @param addGroupPrefix if true then add prefix to each sub-group items
     * @return filtered values
     */
    public List<String> getFilterValues(String filterValue, boolean addGroupPrefix) {
        List<String> filteredItems;
        List<String> results = new ArrayList<>();
        if (group.equals(filterValue)) {
            results.add(group);
            filteredItems = items;
        } else {
            filteredItems = getFilteredGroupValues(filterValue);
        }
        if (addGroupPrefix) filteredItems = addPrefixListItems(group, filteredItems);
        results.addAll(filteredItems);
        return results;
    }

}

