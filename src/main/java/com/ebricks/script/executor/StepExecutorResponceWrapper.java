package com.ebricks.script.executor;

import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

import java.util.ArrayList;
import java.util.List;

public class StepExecutorResponceWrapper {
    private List<StepExecutorResponse> stepExecutorResponses = new ArrayList<>();

    public List<StepExecutorResponse> getStepExecutorResponses() {
        return stepExecutorResponses;
    }

    public void setStepExecutorResponses(List<StepExecutorResponse> stepExecutorResponses) {
        this.stepExecutorResponses = stepExecutorResponses;
    }

}
