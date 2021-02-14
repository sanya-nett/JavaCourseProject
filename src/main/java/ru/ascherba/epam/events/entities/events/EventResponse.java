package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class EventResponse {
    @SerializedName("total")
    public Integer total;
    @SerializedName("period")
    public String period;
    @SerializedName("period_counts")
    public PeriodCounts periodCounts;
    @SerializedName("events")
    public List<Event> events = null;
    @SerializedName("nearest_events")
    public NearestEvents nearestEvents;
}
