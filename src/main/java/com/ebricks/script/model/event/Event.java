package com.ebricks.script.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.JSONPropertyIgnore;

@JsonTypeInfo(

        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        visible = true,
        property = "type"
)
@JsonSubTypes({

        @JsonSubTypes.Type(value = TapEvent.class, name = "tap"),
        @JsonSubTypes.Type(value = BackEvent.class, name = "back"),
        @JsonSubTypes.Type(value = LockEvent.class, name = "lock"),
        @JsonSubTypes.Type(value = UnlockEvent.class, name = "unlock"),
        @JsonSubTypes.Type(value = HomeEvent.class, name = "home"),
        @JsonSubTypes.Type(value = LaunchEvent.class, name = "launch"),
        @JsonSubTypes.Type(value = InputEvent.class, name = "input"),
        @JsonSubTypes.Type(value = SwipeEvent.class, name = "swipe"),
        @JsonSubTypes.Type(value = EndEvent.class, name = "end"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @JsonIgnore
    private String type;
    private long eventTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }
}
