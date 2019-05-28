package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

public class BackExecutor extends StepExecutor {

    public BackExecutor(Step step) {
        super(step);
    }

    public StepExecutorResponse execute() {
        AppiumService.getInstance().back();
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
