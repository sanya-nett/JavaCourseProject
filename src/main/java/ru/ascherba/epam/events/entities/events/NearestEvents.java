package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class NearestEvents {
    @SerializedName("total")
    public Integer total;
    @SerializedName("this_week")
    public List<Object> thisWeek = null;
    @SerializedName("next_week")
    public List<Object> nextWeek = null;
}
