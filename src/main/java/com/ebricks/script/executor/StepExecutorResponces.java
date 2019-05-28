package com.ebricks.script.executor;

import java.util.ArrayList;
import java.util.List;

public class StepExecutorResponces {
    private List<StepExecutorReturnResponse> stepExecutorResponses = new ArrayList<>();

    public List<StepExecutorReturnResponse> getStepExecutorResponse() {
        return stepExecutorResponses;
    }

    public void setStepExecutorResponse(List<StepExecutorReturnResponse> stepExecutorResponse) {
        this.stepExecutorResponses = stepExecutorResponse;
    }

    public void addObject(StepExecutorReturnResponse stepExecutorResponse) {
        this.stepExecutorResponses.add(stepExecutorResponse);
    }
}
