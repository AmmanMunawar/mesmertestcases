package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;

import com.ebricks.script.model.event.SwipeEvent;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;

public class SwipeExecutor extends StepExecutor {
    public SwipeExecutor(Step step) {
        super(step);
    }

    public StepResponse execute(UIElement uiElement) {
        init();
        SwipeEvent swipe = (SwipeEvent) step.getEvent();
        AppiumService.getInstance().swipe(swipe.getStartPointX(), swipe.getStartPointY(), swipe.getEndPointX(), swipe.getEndPointY());
        this.stepResponse.setUiElement(uiElement);
        this.stepResponse.getStepStatus().setStatus(true);
        return this.stepResponse;
    }
}
