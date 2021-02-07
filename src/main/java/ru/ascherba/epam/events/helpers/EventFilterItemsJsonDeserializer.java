package ru.ascherba.epam.events.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.ascherba.epam.events.entities.talks.EventTalkFilterItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandr.scherba on 02.02.2021
 */
public class EventFilterItemsJsonDeserializer implements JsonDeserializer<EventTalkFilterItem> {
    @Override
    public EventTalkFilterItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String group;
        List<String> items = new ArrayList<>();
        if (json.isJsonObject()) {
            group = json.getAsJsonObject().get("group").getAsString();
            json.getAsJsonObject()
                    .getAsJsonArray("items")
                    .iterator()
                    .forEachRemaining(jsonElement -> items.add(jsonElement.getAsString()));
        } else {
            group = json.getAsString();
        }
        return new EventTalkFilterItem(group, items);
    }
}
