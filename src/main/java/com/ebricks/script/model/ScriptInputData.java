package com.ebricks.script.model;

import java.util.ArrayList;
import java.util.List;

public class ScriptInputData {
    private List<Step> steps = new ArrayList<Step>();


    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
