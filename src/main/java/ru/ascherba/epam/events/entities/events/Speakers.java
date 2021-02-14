package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class Speakers {
    @SerializedName("photos")
    public List<Object> photos = null;
    @SerializedName("count")
    public Integer count;
}
