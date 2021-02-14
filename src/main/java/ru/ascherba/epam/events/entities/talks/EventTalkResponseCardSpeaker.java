package ru.ascherba.epam.events.entities.talks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aleksandr.scherba on 07.02.2021
 * Created automatically for POJO description
 */
public class EventTalkResponseCardSpeaker {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("job_position")
    public String jobPosition;
}
