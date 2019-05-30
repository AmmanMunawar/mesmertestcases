package com.ebricks.script.model;

import com.ebricks.script.model.event.Event;
import com.ebricks.script.stepexecutor.response.Screen;

public class Step {

    private UIElement element;
    private Event event;
    private int id;
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
