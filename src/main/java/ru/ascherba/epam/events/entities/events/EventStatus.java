package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class EventStatus {
    @SerializedName("class")
    public String _class;
    @SerializedName("text")
    public String text;
}
