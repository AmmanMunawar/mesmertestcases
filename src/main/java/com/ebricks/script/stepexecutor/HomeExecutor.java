package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

public class HomeExecutor extends StepExecutor {

    public HomeExecutor(Step step) {
        super(step);
    }

    public StepExecutorResponse execute() {

        AppiumService.getInstance().home();
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
