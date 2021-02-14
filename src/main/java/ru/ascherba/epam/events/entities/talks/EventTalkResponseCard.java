package ru.ascherba.epam.events.entities.talks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 07.02.2021
 * Created automatically for POJO description
 */
public class EventTalkResponseCard {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("event_title")
    public String eventTitle;
    @SerializedName("event_url")
    public String eventUrl;
    @SerializedName("date")
    public String date;
    @SerializedName("language")
    public String language;
    @SerializedName("speakers")
    public List<EventTalkResponseCardSpeaker> speakers = null;
}
