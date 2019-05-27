package com.ebricks.script.model.event;


public class SwipeEvent extends Event {
    private int startPointX;
    private int startPointY;
    private int endPointX;
    private int endPointY;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartPointX() {
        return startPointX;
    }

    public void setStartPointX(int startPointX) {
        this.startPointX = startPointX;
    }

    public int getStartPointY() {
        return startPointY;
    }

    public void setStartPointY(int startPointY) {
        this.startPointY = startPointY;
    }

    public int getEndPointX() {
        return endPointX;
    }

    public void setEndPointX(int endPointX) {
        this.endPointX = endPointX;
    }

    public int getEndPointY() {
        return endPointY;
    }

    public void setEndPointY(int endPointY) {
        this.endPointY = endPointY;
    }


}
