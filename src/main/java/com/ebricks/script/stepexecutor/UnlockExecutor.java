package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class UnlockExecutor extends StepExecutor {

    public UnlockExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().unlockDevice();
        return this.stepExecutorResponse;
    }
}
