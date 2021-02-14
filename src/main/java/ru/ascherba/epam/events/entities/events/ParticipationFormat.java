package ru.ascherba.epam.events.entities.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aleksandr.scherba on 14.02.2021
 * Created automatically for POJO description
 */
public class ParticipationFormat {
    @SerializedName("online")
    public Boolean online;
    @SerializedName("online_label")
    public String onlineLabel;
    @SerializedName("indoor")
    public Boolean indoor;
}
