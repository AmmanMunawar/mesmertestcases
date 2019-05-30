package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

public class LaunchExecutor extends StepExecutor {

    public LaunchExecutor(Step step) {
        super(step);
    }

    public StepExecutorResponse execute() {

        AppiumService.getInstance().launchApp();
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
