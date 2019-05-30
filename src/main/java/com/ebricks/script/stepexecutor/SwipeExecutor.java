package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.event.SwipeEvent;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

public class SwipeExecutor extends StepExecutor {

    public SwipeExecutor(Step step) {
        super(step);
    }

    public StepExecutorResponse execute() {
        SwipeEvent swipe = (SwipeEvent) step.getEvent();
        AppiumService.getInstance().swipe(
                swipe.getStartPointX(), swipe.getStartPointY(), swipe.getEndPointX(), swipe.getEndPointY());
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
