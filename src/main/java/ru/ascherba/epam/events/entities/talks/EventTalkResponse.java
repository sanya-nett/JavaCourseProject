package ru.ascherba.epam.events.entities.talks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aleksandr.scherba on 07.02.2021
 * Created automatically for POJO description
 */
public class EventTalkResponse {

    @SerializedName("total")
    public Integer total;
    @SerializedName("took")
    public Integer took;
    @SerializedName("start")
    public Integer start;
    @SerializedName("limit")
    public Integer limit;
    @SerializedName("talks")
    public List<EventTalkResponseCard> talks = null;
}
