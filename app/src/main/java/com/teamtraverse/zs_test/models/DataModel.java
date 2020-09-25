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
        "event",
        "owner",
        "caption",
        "video",
        "photo",
        "uploaded"
})
public class DataModel {

    @JsonProperty("event")
    private Event event;
    @JsonProperty("owner")
    private UserLists owner;
    @JsonProperty("caption")
    private String caption;
    @JsonProperty("video")
    private String video;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("uploaded")
    private String uploaded;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("event")
    public Event getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(Event event) {
        this.event = event;
    }

    @JsonProperty("owner")
    public UserLists getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(UserLists owner) {
        this.owner = owner;
    }

    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonProperty("video")
    public String getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(String video) {
        this.video = video;
    }

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("uploaded")
    public String getUploaded() {
        return uploaded;
    }

    @JsonProperty("uploaded")
    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
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
