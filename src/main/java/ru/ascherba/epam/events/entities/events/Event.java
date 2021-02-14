package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class Event {

    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String url;
    @SerializedName("dates")
    public String dates;
    @SerializedName("time")
    public Time time;
    @SerializedName("is_past")
    public Boolean isPast;
    @SerializedName("language")
    public String language;
    @SerializedName("languages")
    public List<String> languages = null;
    @SerializedName("size")
    public String size;
    @SerializedName("participation_format")
    public ParticipationFormat participationFormat;
    @SerializedName("branding")
    public Object branding;
    @SerializedName("event_status")
    public Object eventStatus;
    @SerializedName("speakers")
    public Speakers speakers;
    @SerializedName("in_calendar")
    public Boolean inCalendar;
    @SerializedName("is_calendar")
    public Boolean isCalendar;
    @SerializedName("location")
    public String location;
}
