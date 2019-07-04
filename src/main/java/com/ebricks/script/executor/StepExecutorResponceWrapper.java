package com.ebricks.script.executor;

import com.ebricks.script.model.Step;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StepExecutorResponceWrapper {

    private List<Step> steps = new ArrayList<>();

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
