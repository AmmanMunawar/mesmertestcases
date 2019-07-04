package com.ebricks.script.model;

import com.ebricks.script.assertion.AssertionUDA;
import com.ebricks.script.model.event.Event;
import com.ebricks.script.stepexecutor.response.Screen;
import com.ebricks.script.stepexecutor.response.StepStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    @JsonProperty("element")
    private UIElement element;
    @JsonProperty("event")
    private Event event;
    @JsonProperty("stepId")
    private int stepId;
    @JsonProperty("screen")
    private Screen screen;
    @JsonProperty("domUrl")
    private String domUrl;
    @JsonProperty("result")
    private StepStatus result;
    @JsonProperty("uda")
    private List<AssertionUDA> uda = new ArrayList<AssertionUDA>();

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public UIElement getElement() {
        return element;
    }

    public void setElement(UIElement element) {
        this.element = element;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDomUrl() {
        return domUrl;
    }

    public void setDomUrl(String domUrl) {
        this.domUrl = domUrl;
    }

    public StepStatus getResult() {
        return result;
    }

    public void setResult(StepStatus result) {
        this.result = result;
    }

    public List<AssertionUDA> getUda() {
        return uda;
    }

    public void setUda(List<AssertionUDA> uda) {
        this.uda = uda;
    }

}
