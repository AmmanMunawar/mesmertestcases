package com.ebricks.script.assertion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UDAElement {

    @JsonProperty("position")
    private List<Integer> position = new ArrayList<Integer>();//": [1198, 2230],
    @JsonProperty("size")
    private List<Integer> size = new ArrayList<Integer>();// [242, 162],
    @JsonProperty("value")
    private String value;//": "Aman",
    @JsonProperty("display")
    private boolean display;//": true

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
