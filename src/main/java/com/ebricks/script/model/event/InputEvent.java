package com.ebricks.script.model.event;

import com.ebricks.script.executor.ScriptExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputEvent extends Event {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private String text;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
