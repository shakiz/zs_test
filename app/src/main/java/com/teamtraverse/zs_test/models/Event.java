package com.teamtraverse.zs_test.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "start_event",
        "end_event"
})
public class Event {

    @JsonProperty("title")
    private String title;
    @JsonProperty("start_event")
    private String startEvent;
    @JsonProperty("end_event")
    private String endEvent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("start_event")
    public String getStartEvent() {
        return startEvent;
    }

    @JsonProperty("start_event")
    public void setStartEvent(String startEvent) {
        this.startEvent = startEvent;
    }

    @JsonProperty("end_event")
    public String getEndEvent() {
        return endEvent;
    }

    @JsonProperty("end_event")
    public void setEndEvent(String endEvent) {
        this.endEvent = endEvent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}