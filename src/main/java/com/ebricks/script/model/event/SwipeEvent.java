package com.ebricks.script.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwipeEvent extends Event {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int touchDuration;

    public SwipeEvent(){
        setType("swipe");
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getTouchDuration() {
        return touchDuration;
    }

    public void setTouchDuration(int touchDuration) {
        this.touchDuration = touchDuration;
    }
}
