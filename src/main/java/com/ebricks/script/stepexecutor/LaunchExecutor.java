package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class LaunchExecutor extends StepExecutor {

    public LaunchExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().launchApp();
        return this.stepExecutorResponse;
    }
}
