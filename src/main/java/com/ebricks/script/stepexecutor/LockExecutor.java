package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;

public class LockExecutor extends StepExecutor {
    public LockExecutor(Step step) {
        super(step);
    }

    public void init() {
        AppiumService.getInstance().getScreenShotAs(this.step.getId());
    }


    public StepExecutorResponse execute() {
        AppiumService.getInstance().lockDevice();
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
