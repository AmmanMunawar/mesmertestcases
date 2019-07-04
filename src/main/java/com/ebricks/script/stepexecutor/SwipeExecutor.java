package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.event.SwipeEvent;
import com.ebricks.script.service.AppiumService;

public class SwipeExecutor extends StepExecutor {

    public SwipeExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        SwipeEvent swipe = (SwipeEvent) step.getEvent();
        AppiumService.getInstance().swipe(
                swipe.getStartX(), swipe.getStartY(), swipe.getEndX(), swipe.getEndY(), swipe.getTouchDuration());
        return this.stepExecutorResponse;

    }
}
